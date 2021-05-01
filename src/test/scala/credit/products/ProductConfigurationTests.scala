package credit.products

import org.scalatest.FunSuite

class ProductConfigurationTests extends FunSuite{

  test("toString returns the class setters"){
    val product = new ProductConfiguration(id = "ABC", location = "/tmp", extension = ".csv")
    assert(product.toString == "ProductConfiguration\n'id' -> ABC,\n'location' -> /tmp,\n'extension' -> .csv\n")
  }

  test("toMap returns a map of getters"){
    val product = new ProductConfiguration(id = "ABC", location = "/tmp", extension = ".csv")
    val testMap = product.toMap
    assert(testMap("id") == "ABC")
    assert(testMap("location") == "/tmp")
    assert(testMap("extension") == ".csv")
  }
}
