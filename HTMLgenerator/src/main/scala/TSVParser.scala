import java.io.{FileReader, File}
import au.com.bytecode.opencsv.CSVReader
import com.typesafe.scalalogging.StrictLogging

class TSVParser extends StrictLogging {

  val PROPER_NR_OF_ELEMENTS = 3

  def parseFile(file: File): List[Link] = {
    import scala.collection.JavaConversions._
    val csvReader: CSVReader = new CSVReader(new FileReader(file), '\t', '\n', 0)
    val myEntries: List[Array[String]] = csvReader.readAll().toList
    logger.info("entries " + myEntries.size)
    myEntries.collect { case e if e.length == PROPER_NR_OF_ELEMENTS => Link(e) }
  }
}
