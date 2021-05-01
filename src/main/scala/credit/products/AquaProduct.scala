package credit.products

import org.apache.spark.sql.functions.{input_file_name, lit}
import org.apache.spark.sql.{DataFrame, SparkSession}

import java.time.Instant

/**
 * Applications for the Aqua product
 * */
class AquaProduct(configurationFactory: ProductConfigurationFactory) extends credit.products.Product {
  override val id: String = "AQUA"
  override val terms: String = "The terms and conditions are....."
  override val interestRate: Double = 17.5
  override val limit: Double = 7500
  override val minSalary: Double = 5000
  override val productConfiguration: Map[String, String] = configurationFactory.getProductConfiguration("AQUA")
  override val path: String = s"$location/*$extension"
  private val extension: String = configurationFactory.getExtension(id)
  private val location: String = configurationFactory.getLocation(id)

  override def processApplicants(at: Instant): DataFrame = {

    // read the new product applications into a dataframe
    SparkSession.builder().getOrCreate().read
      .json(s"$path")
      .na.drop("all")
      .withColumn("processed_at", lit(java.sql.Timestamp.from(at)))
      .withColumn("source", input_file_name())
  }
}
