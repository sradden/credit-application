name := "credit-application"
version := "0.1"

val sparkVersion = "2.2.0"

// group id,artefact id, version
// specify %% if you want sbt to resolve the scala version
// specify % if you require a specific version of scala
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.2.0" % "provided"
libraryDependencies += "org.apache.spark" %%"spark-sql" % "2.2.0" % "provided"
libraryDependencies += "org.scalatest" % s"scalatest_${scalaBinaryVersion.value}" % sparkVersion % "test"

// Build section
lazy val commonSettings = Seq(
    version:="0.1-SNAPSHOT",
    scalaVersion := "2.11.8"
)

lazy val creditApp: Project = (project in file("."))
  .settings(commonSettings: _*)
  .settings(
      assembly / mainClass := Some("CreditProductApp"),
      assembly / assemblyJarName := "credit.application.jar",
      assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false),
      assemblyOutputPath in assembly := file("build/out/"),
      assemblyMergeStrategy in assembly := {
      case PathList("javax", "servlet", xs @ _*) => MergeStrategy.last
      case PathList("javax", "activation", xs @ _*) => MergeStrategy.last
      case PathList("org", "apache", xs @ _*) => MergeStrategy.last
      case PathList("com", "google", xs @ _*) => MergeStrategy.last
      case PathList("com", "esotericsoftware", xs @ _*) => MergeStrategy.last
      case PathList("com", "codahale", xs @ _*) => MergeStrategy.last
      case PathList("com", "yammer", xs @ _*) => MergeStrategy.last
      case "about.html" => MergeStrategy.rename
      case "META-INF/ECLIPSEF.RSA" => MergeStrategy.last
      case "META-INF/mailcap" => MergeStrategy.last
      case "META-INF/mimetypes.default" => MergeStrategy.last
      case "plugin.properties" => MergeStrategy.last
      case "log4j.properties" => MergeStrategy.last
      case x =>
        val oldStrategy = (assemblyMergeStrategy in assembly).value
        oldStrategy(x)
    }
  )
