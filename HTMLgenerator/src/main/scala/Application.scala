import java.io.{PrintWriter, File}
import com.typesafe.scalalogging.StrictLogging

object Application extends StrictLogging {

  val tsvFilePath = "src/main/resources/gify_lista.tsv"
  val rand = new scala.util.Random
  val NR_OF_GIFS_EACH_FILE = 20

  def main(args: Array[String]): Unit = {
    logger.debug("Begin")
    // 1. Read tsv into a list of Link objects
    val linkObjects: List[Link] = new TSVParser().parseFile(new File(tsvFilePath))
    val NUMBER_OF_FILES = linkObjects.size / NR_OF_GIFS_EACH_FILE
    logger.info("nr of files: " + NUMBER_OF_FILES)
    // 2. Prepare index names array for infinity scroll
    val names:Array[String] = new Array[String](1001)
    for (iter <- 0 to 1000) {
      names(iter)= "index" + iter + ".html"
    }
    // 3. Convert the list of Link objects to html files
    for(j <- 0 to NUMBER_OF_FILES-1) {
      val a = (j*NR_OF_GIFS_EACH_FILE)+0
      val b = (j*NR_OF_GIFS_EACH_FILE)+NR_OF_GIFS_EACH_FILE-1
      new PrintWriter(new File(names(j+1))) {
        write("<div id=\"content\">\n")
        for (i <- a to b) {
          write("<img src=\"" + linkObjects(i).url + "\">\n")
        }
        write("</div>")
        close
      }
    }
    logger.debug("end.")
  }
}
