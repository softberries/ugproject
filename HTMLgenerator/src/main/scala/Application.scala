import java.io.{PrintWriter, File}
import com.typesafe.scalalogging.StrictLogging

object Application extends StrictLogging {

  val csvFilePath = "src/main/resources/hadoop_output_no_header.csv"
  val rand = new scala.util.Random
  val NR_OF_GIFS_EACH_FILE = 40
  val NO_OF_LINES = 1487855 //nr of lines in hadoop_output_no_header.csv

  def main(args: Array[String]): Unit = {
    logger.debug("Begin")
    // 1. Read tsv into a list of Link objects
    val linkObjects: List[Link] = new CSVParser().parseFile(new File(csvFilePath))
    val NUMBER_OF_FILES: Int = linkObjects.size / NR_OF_GIFS_EACH_FILE
    logger.info("nr of files: " + NUMBER_OF_FILES)
    // 2. build index part files
    for (num <- 0 to NUMBER_OF_FILES) {
      buildSingleHtmlPart(num, linkObjects)
    }
    logger.debug("end.")
  }

  def buildSingleHtmlPart(pageNumber: Int, allLinks: List[Link]) = {
    val startIndex: Int = pageNumber * NR_OF_GIFS_EACH_FILE
    val endIndex: Int = startIndex + NR_OF_GIFS_EACH_FILE
    val elements: List[Link] = allLinks.slice(startIndex, endIndex)
    if (elements.size > 0) {
      new PrintWriter(new File("out/index" + pageNumber + ".html")) {
        write("<div id=\"content\">\n")
        for (link <- elements) {
          write("<img src=" + link.url + ">\n")
        }
        write("</div>")
        close
      }
    }
  }
}
