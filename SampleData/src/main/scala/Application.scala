import java.io.{PrintWriter, File}
import com.typesafe.scalalogging.StrictLogging
import play.api.libs.json.{JsValue}
import play.libs.Json

object Application extends StrictLogging {

  val csvFilePath = "/Users/kris/Documents/Studia/projektgrupowy/ugproject/SampleData/src/test/resources/sample_data_1000_records.csv"
  val jsonFilePath = "/Users/kris/Documents/Studia/projektgrupowy/ugproject/SampleData/src/test/resources/sample_json_data.json"
  val rand = new scala.util.Random

  def main(args: Array[String]): Unit = {
    logger.debug("Starting application..")
    // 1. Read csv into a list of Reddit objects
    val redditObjects: List[RedditComment] = new CSVParser().parseFile(new File(csvFilePath))
    logger.info("Testing random comment: " + redditObjects(rand.nextInt(100)).body)
    // 2. Convert the list of Reddit object to JSON array
    val json: JsValue = new JSONFactory().createJSONObject(redditObjects)
    logger.info("Testing another comment: " + json(rand.nextInt(100)).\("body"))
    // 3. Save the JSON array to a file
    new PrintWriter(jsonFilePath) {
      write(json.toString()); close
    }
    logger.debug("finished.")
  }
}
