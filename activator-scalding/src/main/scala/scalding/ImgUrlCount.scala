import com.twitter.scalding._

class ImgUrlCount(args: Args) extends Job(args) {

  val imgUrlRegex = """https?://[^/\s]+/\S+\.(jpg|png|gif)""".r
  val maxNumberOfImages = 1000

  // Read the file specified by the --input argument and process each line
  // by extracting the urls pointing to images (jpg/png/gif)
  // The first argument list to flatMap specifies that we pass the 'line field
  // to the anonymous function on each call and each url in the returned
  // collection of strings is given the name 'imgurl. Note that TextLine automatically
  // associates the name 'line with each line of text. It also tracks the line number
  // and names that field 'offset. Here, we're dropping the offset.
  TextLine(args("input"))
    .read
    .flatMap('line -> 'imgurl) {
      line: String => imgUrlRegex.findFirstIn(line)
    }

    // At this point we have a stream of image urls in the pipeline. To count
    // occurrences of the same url, we need to group the urls together.
    // The groupBy operation does this. The first argument list to groupBy
    // specifies the fields to group over as the key. In this case, we only
    // use the 'imgurl field.
    // The anonymous function is passed an object of type com.twitter.scalding.GroupBuilder.
    // All we need to do is compute the size of the group and we give it an
    // optional name, 'count.
    .groupBy('imgurl) { group => group.size('count) }

    // At the end we sort the grouped image urls to have the most frequent ones at the top
    // and once sorted we take the first 1000 of them for further project needs.
    .groupAll { _.sortBy('count).reverse.take(maxNumberOfImages) }

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

/*

 */