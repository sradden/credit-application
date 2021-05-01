package credit.products

class ProductConfigurationFactory(factory: Map[String, Map[String,String]] =
                                  ProductConfigurationBuilder.defaults.map{ case (str, configuration) => (str, configuration.toMap)}){

  /**
   * Determines if a [[ProductConfiguration]] has been built with the supplied id.
   *
   * @param id the id of the [[ProductConfiguration]] to check if exists
   * @return true if the [[ProductConfiguration]] exists
   * @throws IllegalArgumentException if a [[ProductConfiguration]] with the supplied id
   *                                  does not exist
   * */
  def hasProduct(id: String): Boolean = {
    require(factory.contains(id),s"No ProductConfiguration exists with id $id")
    true
  }

  /**
   * Returns the [[ProductConfiguration]] having the supplied id otherwise
   * throws IllegalArgumentException
   *
   * @param id the id of the [[ProductConfiguration]].
   * @throws IllegalArgumentException if the [[ProductConfiguration]] does not exist
   * */
  def getProductConfiguration(id: String): Map[String, String] = {
    hasProduct(id)
    factory.getOrElse(id, factoryDefault)
  }

  /**
   * Returns the location where Products are downloaded from.
   *
   * @param id the id of the [[ProductConfiguration]].
   * @return if exists, the [[ProductConfiguration]] having the specified id.
   * @throws IllegalArgumentException if the specified [[ProductConfiguration]] with the supplied id does not exist.
   *
   * */
  def getLocation(id: String): String = {
    hasProduct(id)
    this.getProductConfiguration(id).getOrElse("location", factoryDefault("location"))
  }

  /**
   * Returns the file extension of the specified Product
   *
   * @param id the id of the [[ProductConfiguration]].
   * @return if exists, the [[ProductConfiguration]] having the specified id.
   * @throws IllegalArgumentException if the specified [[ProductConfiguration]] does not exist.
   * */
  def getExtension(id: String): String = {
    hasProduct(id)
    this.getProductConfiguration(id).getOrElse("extension", factoryDefault("extension"))
  }

  /**
   * Returns a sequence of configured Product id's
   * */
  def getConfiguredProducts:Seq[String] = factory.keys.toSeq

  /**
   * Provides a default dummy implementation in case the factory has problems returning a specified [[ProductConfiguration]]
   * */
  private val factoryDefault: Map[String, String] = {
  Map("id"->"DUMMY", "location"->"/tmp", "extension"->".DUMMY")
  }
}
