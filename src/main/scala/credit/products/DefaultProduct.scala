package credit.products
import org.apache.spark.sql.{DataFrame, SparkSession}

import java.time.Instant

class DefaultProduct(configurationFactory: ProductConfigurationFactory) extends Product {
  override val id: String = "DUMMY"
  override val terms: String = ""
  override val interestRate: Double = 0
  override val limit: Double = 0
  override val minSalary: Double = 0
  override val productConfiguration: Map[String, String] = configurationFactory.getProductConfiguration(id)
  override val path: String = ""

  override def processApplicants(at: Instant): DataFrame = {
    SparkSession.builder().getOrCreate().emptyDataFrame
  }
}
