import org.scalatest.FunSuite

class CreditProductAppTests extends FunSuite{

  test("Constructor app arguments set field values"){

    val args: Map[Symbol, Any] = Map('hadoop_dir -> "c:\tmp", 'cores -> 7, 'app_name -> "TestingConsoleApp")
    val app = new CreditProductApp(args)

    // App class doesn't init val' unless main is called
    app.main(Array())

    assert(app.hadoopDir == "c:\tmp")
    assert(app.cores == 7)
    assert(app.appName == "TestingConsoleApp")
  }

  test("Pass empty map to .ctor results in default field values") {

    val app = new CreditProductApp(Map())
    app.main(Array())

    assert(app.hadoopDir == "c:/temp/hadoop-home/")
    assert(app.appName == "ConsoleApp")
    assert(app.cores == 3)
  }

  test("Specify non-numeric number of cores defaults to 3 cores"){
    val args: Map[Symbol, Any] = Map('cores -> "foobar")
    val app = new CreditProductApp(args)

    app.main(Array())

    assert(app.cores == 3)
  }
}
