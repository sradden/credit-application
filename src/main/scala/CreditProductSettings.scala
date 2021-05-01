class CreditProductSettings(settings: Map[Symbol, Any]) extends SparkConfiguration with ProductConfiguration {

  override def hadoopDir: String = settings.getOrElse('hadoop_dir, super.hadoopDir).toString

  override def cores: Int = {

    settings.get('cores) match {
      case Some(x) =>
        try Integer.parseInt(x.toString)
          // value specified not a valid integer
        catch {
          case _: NumberFormatException => super.cores
        }
      case None => super.cores
    }
  }

  override def productId: String = settings.getOrElse('product, super.productId).toString
  override def applicantResultPath: String = settings.getOrElse('location, super.applicantResultPath).toString

  lazy val appName: String = settings.getOrElse('app_name, appNameDefault).toString
  private val appNameDefault: String = s"${getClass.getName}"
}

/**
 * Companion object returns [[CreditProductSettings]] with default values
 */
object CreditProductSettings {
  def apply() = new CreditProductSettings(Map())
}

trait SparkConfiguration {
  /**
   *
   * @return path to winutils.exe
   */
  def hadoopDir: String = "c:/temp/hadoop-home/"

  /**
   *
   * @return the number of spark cores
   */
  def cores = 3
}

trait ProductConfiguration {
  /**
   * The id of [[credit.products.Product]]. Use ALL to register all products.
   */
  def productId = "ALL"

  /**
   * The location where the product application result parquet files are stored.
   */
  def applicantResultPath = "src/main/resources/applicant-result"
}