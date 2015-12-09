import java.io.{FileReader, File}
import au.com.bytecode.opencsv.CSVReader

class CSVParser {

  def parseFile(file: File): List[RedditComment] = {
    val file = "sample_data.csv"
    val csvReader: CSVReader = new CSVReader(new FileReader(file), ',', '"', 1)
    val myEntries = csvReader.readAll()
    println("entries " + myEntries.size())

    /* test what is in the first element of myEntries
    val firstOne = myEntries.get(0)
    val sizeOfFirstOne = myEntries.get(0).size
    for (x <- 0 to sizeOfFirstOne - 1) {
      println(firstOne(x))
    }
    println(sizeOfFirstOne)*/

    val buffer = scala.collection.mutable.ListBuffer.empty[RedditComment]
    for(i <- 0 to myEntries.size() - 1 ){  // for i <- 0 to 10600 works perfect
      if(i > 10600) println(i) //finding bug
      var comment = new RedditComment(myEntries.get(i)(0), myEntries.get(i)(1), myEntries.get(i)(2), myEntries.get(i)(3),
        myEntries.get(i)(4), myEntries.get(i)(5), myEntries.get(i)(6), myEntries.get(i)(7), myEntries.get(i)(8),
        myEntries.get(i)(9), myEntries.get(i)(10), myEntries.get(i)(11), myEntries.get(i)(12), myEntries.get(i)(13),
        myEntries.get(i)(14), myEntries.get(i)(15), myEntries.get(i)(16), myEntries.get(i)(17), myEntries.get(i)(18),
        myEntries.get(i)(19), myEntries.get(i)(20), myEntries.get(i)(21), myEntries.get(i)(22))
      buffer += comment
    }
    buffer.toList
    // show random comment
    val rand = new scala.util.Random
    println(buffer(rand.nextInt(10000)).body)
    //println(file.canRead)
    List.empty[RedditComment]
  }
}
