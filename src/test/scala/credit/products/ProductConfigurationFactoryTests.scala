package credit.products

import org.scalatest.FunSuite

class ProductConfigurationFactoryTests extends FunSuite{

  test("hasProduct returns true when the specified id exists"){
    val factory = new ProductConfigurationFactory()
    assert(factory.hasProduct("AQUA"))
  }

  test("hasProduct throws IllegalArgumentException when a specific ProductConfiguration does not exist."){
    val factory = new ProductConfigurationFactory()
    intercept[IllegalArgumentException] {
      factory.hasProduct("FOO")
    }
  }

  test("getLocation returns the download location for the specified id"){
    val factory = new ProductConfigurationFactory()
    assert(factory.getLocation("AQUA") == "src/test/data/product-applications/")
  }

  test("getLocation throws IllegalArgumentException when ProductConfiguration does not exist"){
    val factory = new ProductConfigurationFactory()
    intercept[IllegalArgumentException] {
      factory.getLocation("FOO")
    }
  }

  test("getExtension returns the file extension for the specified Product id."){
    val factory = new ProductConfigurationFactory()
    assert(factory.getExtension("AQUA") == "AQUA-applications.json")
  }

  test("getExtension throws IllegalArgumentException when ProductConfiguration does not exist.") {
    val factory = new ProductConfigurationFactory()
    intercept[IllegalArgumentException] {
      factory.getExtension(".foo")
    }
  }

  test("getConfiguredProducts returns the configured product id's"){
      val factory = new ProductConfigurationFactory()
      assert(factory.getConfiguredProducts.size == 3)
    }
  }
