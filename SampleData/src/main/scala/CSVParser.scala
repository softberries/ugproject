import java.io.File

class CSVParser {

  def parseFile(file: File): List[RedditComment] = {
    println(file.canRead)
    List.empty[RedditComment]
  }

}
