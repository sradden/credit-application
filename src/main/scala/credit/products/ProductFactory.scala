package credit.products

/**
 * [[ProductFactory]] returns [[Map]] of configured [[Product]]
 * */
class ProductFactory (configurationFactory: ProductConfigurationFactory){

  // retrieves a list of configured products
  val registeredProducts: Map[String, Product] = Map(
    "AQUA"-> new AquaProduct(configurationFactory)
    // TODO addition products here as they go live
  )
}

// companion object to support the instantiation of Products
object ProductFactory{

  /**
   * apply creates a sequence of enabled [[Product]]'
   * @param productId the id of the [[Product]] return
   * @param configurationFactory the factory that builds a [[Product]]
   * @return [[Seq]] of enabled [[Product]]
   */
  def apply(productId: String, configurationFactory: ProductConfigurationFactory): Seq[Product] = {

  // what Products applications do we process?
  val enabledProducts = if (productId.toUpperCase() == "ALL")
    configurationFactory.getConfiguredProducts.filter(!_.eq("DUMMY"))
  else
    Seq(productId.toUpperCase)

  // if we don't have a Product with specified id then return default implementation
  enabledProducts.map(productId => {
    if (configurationFactory.getConfiguredProducts.contains(productId.toUpperCase) && productId !="DUMMY")
      new ProductFactory(configurationFactory).registeredProducts(productId)
    else
      // possible throw exception here?
     new DefaultProduct(configurationFactory)
  })
  }
}