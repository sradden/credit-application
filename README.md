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
