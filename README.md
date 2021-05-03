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
val app: CreditProductApp = new CreditProductApp()
```
`CreditProductSettings` can be instantiated by passing a `Map[Symbol, Any]` as an argument. The following Map shows the settings that can be specified:
```scala
Map(`cores -> 3, `hadoop_dir -> "hadoop path", `product -> "AQUA", `app_name -> "name of app", `location -> "output path for result of application")
```
If you pass an empty Map as an argument then default values will be applied.

To run the application using default settings enter the following command:

```scala
new CreditProductApp(CreditProductSettings.apply()).main(Array())
```
## Configuring and Building Products
The `Product` trait represents a credit product that consumers are applying for. Specifc cards i.e. AQUA extend the `Product` trait with implementation details specific to the product. Each `Product` is configured using a `ProductConfiguration` class and returned via a `ProductConfigurationFactory`. `ProductConfigurationFactory` takes a `ProductConfigurationBuilder` to create instances of `ProductConfiguration` so if you want to on-board new products then you will add these here.

## AQUA Product
The `AquaProduct` extends the `Product` trait with implementation specific to the Aqua credit card. Applications for this card are received in .json format so the class reads the new applications into a spark `DataFrame` and validates the applications by obtaining credit scores from the `CreditRating` object for each applicant before applying business rules to determine the validity of each application. The results are returned to the CreditProductApp to be written to disk in `parquet` format.

## parquet Output
The result of each product application are written to disk in `parquet` format, partitioned by product id and processed timestamp
![image](https://user-images.githubusercontent.com/17062331/116939979-20fc1d80-ac65-11eb-8d72-941fd65eca99.png)
