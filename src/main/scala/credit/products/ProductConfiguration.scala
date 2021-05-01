package credit.products

/**
 * Product configuration for a new application
 * */
case class ProductConfiguration(
                                 id: String,
                                 location: String,
                                 extension: String
                               ) {

  def toMap: Map[String, String] = Map("id" -> id, "location" -> location, "extension" -> extension)

  override def toString: String = s"ProductConfiguration\n'id' -> ${id},\n'location' -> ${location},\n'extension' -> ${extension}\n"
}
