import org.scalatest.FunSuite

class CreditProductSettingsTests extends FunSuite{


  val app: CreditProductApp = new CreditProductApp()

  test("Pass constructor arguments sets field values"){

    val args: Map[Symbol, Any] =
      Map('hadoop_dir -> "c:/tmp/", 'cores -> 7, 'app_name -> "TestingConsoleApp", 'product -> "AQUA", 'location -> "c:/temp/out/")
    val settings: CreditProductSettings = new CreditProductSettings(args)

    assert(settings.hadoopDir == "c:/tmp/")
    assert(settings.cores == 7)
    assert(settings.appName == "TestingConsoleApp")
    assert(settings.productId == "AQUA")
    assert(settings.applicantResultPath == "c:/temp/out/")
  }

  test("No constructor arguments sets default field values") {

    val settings: CreditProductSettings = CreditProductSettings.apply()

    assert(settings.hadoopDir == "c:/temp/hadoop-home/")
    assert(settings.appName == "CreditProductSettings")
    assert(settings.cores == 3)
    assert(settings.productId == "ALL")
    assert(settings.applicantResultPath == "applicant-result")
  }

  test("Specify non-numeric number of cores defaults to 3 cores"){

    val settings: CreditProductSettings = new CreditProductSettings(Map('cores -> "foobar"))
    assert(settings.cores == 3)
  }
}
