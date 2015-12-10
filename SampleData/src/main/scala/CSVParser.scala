import java.io.{FileReader, File}
import au.com.bytecode.opencsv.CSVReader
import com.typesafe.scalalogging.StrictLogging

class CSVParser extends StrictLogging {

  val PROPER_NR_OF_ELEMENTS = 23

  def parseFile(file: File): List[RedditComment] = {
    import scala.collection.JavaConversions._
    val csvReader: CSVReader = new CSVReader(new FileReader(file), ',', '"', 1)
    val myEntries: List[Array[String]] = csvReader.readAll().toList
    logger.info("entries " + myEntries.size)
    myEntries.collect { case e if e.length == PROPER_NR_OF_ELEMENTS => RedditComment(e) }
  }


}
