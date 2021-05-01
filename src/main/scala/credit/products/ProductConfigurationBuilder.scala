package credit.products

/**
 * A builder of [[ProductConfiguration]] providing a default implementation
 *
 * @id A unique String identifying the product
 * @location the location where a Product downloads new applications
 * @extension a file extension for Product applications
 * */
case class ProductConfigurationBuilder(
                                        id: String,
                                        location: Option[String] = None,
                                        extension: Option[String] = None
                                      )
{
  /**
   * a collection of pre-defined [[ProductConfiguration]]
   * */
  private val productDefaults: Map[String, ProductConfiguration] = Map(
    "AQUA"-> ProductConfiguration(
      id = "AQUA", location = "src/test/data/card-applications/", extension = ".json"
    ),
    "FLUID"-> ProductConfiguration(
      id = "FLUID", location = "s3://bucket-name", extension = ".parquet`"
    ),
    "DUMMY"-> ProductConfiguration(
      id = "DUMMY", location = "/tmp", extension = ".DUMMY"
    )
  )

  /**
   * sets the location where to download new applications from.
   *
   * @param location the location where to download new applications from
   * @return a [[ProductConfigurationBuilder]] built with the specified location
   * */
  def withLocation(location: String): ProductConfigurationBuilder = this.copy(location = Some(location))

  /**
   * sets the file extension of new [[Product]] applications
   * @param extension the file extension of the product
   * @return a [[ProductConfigurationBuilder]] built with the specified extension
   */
  def withExtension(extension: String): ProductConfigurationBuilder = this.copy(extension = Some(extension))

}

/**
 * a companion object that returns a default builder
 * */
object ProductConfigurationBuilder {
 def defaults: Map[String, ProductConfiguration] = ProductConfigurationBuilder("DUMMY").productDefaults
}