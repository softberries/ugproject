import java.io.{PrintWriter, File}

import com.typesafe.scalalogging.StrictLogging
import play.api.libs.json.JsArray

object Application extends StrictLogging {

  val csvFilePath = ""
  val jsonFilePath = ""

  def main(args: Array[String]): Unit = {
    logger.debug("Starting application..")
    // 1. Read csv into a list of Reddit objects
    val redditObjects: List[RedditComment] = new CSVParser().parseFile(new File(csvFilePath))
    // 2. Convert the list of Reddit object to JSON array
    val json: JsArray = new JSONFactory().createJSONObject(redditObjects)
    // 3. Save the JSON array to a file
    new PrintWriter(jsonFilePath) {
      write(json.toString); close
    }
    logger.debug("finished.")
  }
}
