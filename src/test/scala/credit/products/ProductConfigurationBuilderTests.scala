package credit.products

import org.scalatest.FunSuite

class ProductConfigurationBuilderTests extends FunSuite{

  test("Companion object returns default build configuration"){

    val configDefaults = ProductConfigurationBuilder.defaults
    assert(configDefaults.keys.size == 3)
    assert(configDefaults.contains("AQUA") && configDefaults.contains("FLUID") && configDefaults.contains("DUMMY"))
    val config = configDefaults("AQUA")
    assert(config.extension == "AQUA-applications.json" && config.location == "src/test/data/product-applications/")
  }

  test("withLocation returns a new [[ProductConfigurationBuilder]] with the supplied location"){
    val configBuilder = new ProductConfigurationBuilder("AQUA")
    assert(configBuilder.location.isEmpty)
    val newBuilder = configBuilder.withLocation("s3://some_bucket_name/")
    assert(newBuilder.location.contains("s3://some_bucket_name/"))
  }

  test("withExtension returns a new [[ProductConfigurationBuilder]] with the supplied extension"){
    val configBuilder = new ProductConfigurationBuilder("AQUA")
    assert(configBuilder.extension.isEmpty)
    val newBuilder = configBuilder.withExtension(".csv")
    assert(newBuilder.extension.contains(".csv"))
  }
}
