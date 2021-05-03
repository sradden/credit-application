package credit.products

import credit.score.CreditRating
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{DataFrame, SparkSession}

import java.time.Instant

/**
 * Applications for the Aqua product
 * */
class AquaProduct(configurationFactory: ProductConfigurationFactory) extends credit.products.Product {
  override val id: String = "AQUA"
  override val terms: String = "The terms and conditions of the AQUA product....."
  override val interestRate: Double = 17.5
  override val limit: Double = 7500
  override val minSalary: Double = 5000
  override val productConfiguration: Map[String, String] = configurationFactory.getProductConfiguration("AQUA")
  private val extension: String = configurationFactory.getExtension(id)
  private val location: String = configurationFactory.getLocation(id)
  override val path: String = s"$location$extension"

  private val creditScore = udf ((key: String) => CreditRating.getScore(key))

  override def processApplicants(at: Instant): DataFrame = {

    // read the new product applications into a dataframe.
    // TODO specifying .config is for local testing only. This MUST be removed
    // if deploying to prod
    val applicantsDF: DataFrame = SparkSession.builder().config("spark.master", "local").getOrCreate()
      .read
      .json(s"$path")
      .na.drop("all")
      .withColumn("key",
        upper(concat(col("first_name"), col("surname"), translate(col("dob"), "-", ""))))
      .withColumn("creation_time", from_unixtime(col("creation_time"),"yyyy-MM-dd HH:mm:ss"))
      .withColumn("product_id", lit(id))
      .withColumn("processed_at", lit(java.sql.Timestamp.from(at)))
      .withColumn("source", input_file_name())

    val creditScoresDF: DataFrame = applicantsDF.select("key")
      .withColumn("credit_score", creditScore(col("key")))

    // creditScore returns a structure so we need to flatten into columns
    val flattenedScoreDF: DataFrame = creditScoresDF.select("credit_score.*")

    // business rule to determine success of application. additional rules can be chained on
    val applicationSuccessful = col("score") >= lit(600) && col("convictions") === lit(false)

    // return a dataframe including the result of the application
    applicantsDF.select("*").join(flattenedScoreDF, "key")
        .withColumn("successful", applicationSuccessful)
  }
}
