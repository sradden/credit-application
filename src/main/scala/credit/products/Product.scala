package credit.products

import org.apache.spark.sql.{DataFrame, SparkSession}

import java.time.Instant

trait Product {
  val id: String
  val terms: String
  val interestRate: Double
  val limit: Double
  val minSalary: Double
  val productConfiguration: Map[String, String]
  val path: String
  private val spark: SparkSession = SparkSession.builder().getOrCreate()

  def processApplicants(at: Instant) : DataFrame
}