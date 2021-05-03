package credit.score

case class CreditScore(
                      key: String,
                      salary: Double,
                      convictions: Boolean,
                      score: Double
                      )
/**
 * [[CreditRating]] simulates a micro-service that returns a [[CreditScore]]
 * for the applicants key.
 */
private [credit] object CreditRating {

  def getScore(key: String) : CreditScore ={
    // TODO this could be a call to a database or external API
    // but for now it's just a case statement
    //val key: String = col.expr.asInstanceOf[UTF8String].toString
    key match {
      case "JANEDOE20011223" => new CreditScore(key = key, salary = 10000, convictions = false, score = 600)
      case "JOHNDOE20160123" => new CreditScore(key = key, salary = 0, convictions = true, score = 150)
      case "FREDBLOGGS19750230" => new CreditScore(key=key, salary = 50000, convictions = false, score = 835)
      case _ => new CreditScore(key="UNKNOWN", salary = 0, convictions = false, score = 0)
    }
    }
  }
