case class Link(url: String, occurences: String)

object Link {
  def apply(arr: Array[String]): Link = {
    Link(arr(0), arr(1))
  }
}