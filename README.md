# credit-application
spark/scala project that processes applications for credit cards to determine whether a customer is successful in their application. New applications are pushed to the product processor which runs a series of business rules against the card application before saving the result to a .parquet file.

## Requirements
- spark version 2.2.0

- scala version 2.11.8

## Setup
Add sbt-assembly as a dependency in  `project/plugins.sbt`:

```scala
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "x.y.z")
```
## Build and packaging
`build.sbt` compiles and packages the project into `credit-application.jar`

## Usage
The main class in `credit-application.jar` is `CreditProductApp` that provides a constructor with the following signature:
```scala
CreditProductApp(settings: CreditProductSettings)
```
If specified, `CreditProductSettings` provides specific settings to the app. When not specified, default settings are applied which allows instances of `CreditProductApp` to be created in either of the following ways:
```scala
// user specific settings
val app: CreditProductApp = new CreditProductApp(new CreditProductSettings(Map[Symbol, Any]))
// default settings
val app: CreditProdctApp = new CreditProductApp()
```
