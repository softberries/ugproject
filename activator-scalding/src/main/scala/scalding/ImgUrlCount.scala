import java.text.SimpleDateFormat
import java.util.Date

import com.twitter.scalding._

class ImgUrlCount(args: Args) extends Job(args) {

  val imgUrlRegex = """https?://[^/\s]+/\S+\.(jpg|png|gif)""".r
  val utcDateRegex = """"created_utc":"([0-9]*)"""".r
  val maxNumberOfImages = 1000

  TextLine(args("input"))
    .read
    .flatMapTo('line -> ('imgurl, 'year)) {
      x: String => {
        val url = imgUrlRegex.findFirstIn(x)
        val date = for (m <- utcDateRegex findFirstMatchIn x) yield m group 1
        val sdf = new SimpleDateFormat("yyyy-MM")
        (url, date) match {
          case (Some(x),Some(y)) => Some(x, sdf.format(new Date(y.toLong * 1000)))
          case _ => None
        }

      }
    }
    .groupBy('imgurl, 'year) { group => group.size('count) }
    .groupAll {
    _.sortBy('count).reverse
  }
  .write(Tsv(args("output")))
}


/**
  * Run this example, using default arguments if none are specified.
  */
object ImgUrlCount {
  val name = "ImgUrlCount"
  val message = "Find and count all the image urls in sample_json_data.json file."

  def main(args: Array[String]) {
    if (args.length != 0) {
      Run.run(name, message, args)
    } else {
      Run.run(name, message,
        Array("--local",
          "--input", "data/sample_json_data.json",
          "--output", "output/kjv-imageurl.txt"))
      Run.printSomeOutput("output/kjv-imageurl.txt")
    }
  }
}
