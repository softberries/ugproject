package com.softwarepassion.scalding.emr

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
