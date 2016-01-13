case class Link(url: String, date: String, occurances: String)

object Link {
  def apply(arr: Array[String]): Link = {
    Link(arr(0), arr(1), arr(2))
  }
}