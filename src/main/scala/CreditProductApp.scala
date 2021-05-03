
import credit.products.{ProductConfigurationFactory, ProductFactory}

import java.time.Instant

/**
 * [[CreditProductApp]] is the main class to process new credit applications
 *
 * @param args [[Map[Symbol, Any]] that contains runtime settings. If args is empty then
 *             default values are used
 */
class CreditProductApp(settings: CreditProductSettings = CreditProductSettings.apply()) extends App {

  // setup hadoop dir for local testing
  System.setProperty("hadoop.home.dir", settings.hadoopDir)

  // get a list of configured products
  val productConfigFactory = new ProductConfigurationFactory()
  val products: Seq[credit.products.Product] = ProductFactory(settings.productId, productConfigFactory)

  // For each product, we load the applicants and determine the success of their application.
  // The result is written to a parquet file, partitioned by product id and date of the application.
  processApplicants(products)

  /**
   *
   * @param products a sequence of product applications
   * @return true if applications process without exception else false
   */
  private def processApplicants(products: Seq[credit.products.Product]) : Boolean = {

    // pass the current Instant to indicate when applicants were processed
    val at: Instant = Instant.now()
    try {
      // for each application, output the result to parquet file
      products.foreach(
        _.processApplicants(at)
          .write
          .partitionBy("product_id", "processed_at")
          .parquet(s"${settings.applicantResultPath}"))
      true
    }
    catch {
    // TODO add logger class to log exception
    case _: Exception => false
    }
  }
}
