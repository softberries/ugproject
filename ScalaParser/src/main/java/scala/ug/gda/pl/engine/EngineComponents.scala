package scala.ug.gda.pl.engine

import java.io.{BufferedReader, FileReader}

import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory


import scala.collection.mutable.ListBuffer
import scala.reflect.internal.util.StringOps

/**
 * Created by Maciek on 2015-12-05.
 */
object EngineComponents {
  val logger = Logger(LoggerFactory.getLogger(this.getClass))
  logger.debug("Here goes my debug message.")

  def printGifList(gifList: List[Gif]) {

    logger.info("Start printing GifList ")

    println("START")
    gifList.foreach(gif => println(gif.toString))
    println("\nEND")
  }

  def generateGifsList(): List[Gif] = {
    logger.info(" Start generating gif list ")
    val bufferedReader = new BufferedReader(new FileReader(Constans.source))
    var line: String = ""

    val listTemp = new ListBuffer[Gif]()
    while ( {
      line = bufferedReader.readLine();
      line != null
    }) {
      val wordList: List[String] = StringOps.words(line)
      wordList.foreach(word => filter(word, listTemp))

    }
    return listTemp.toList
  }


  def filter(link: String, listOfGift: ListBuffer[Gif]) {
    if (link.contains(Constans.GIF)) {
      val gifName = getNameOfGif(link)
      listOfGift += new Gif(gifName, link)
    }
  }

  def getNameOfGif(link: String): String = {
    val words: Array[String] = link.split("/")
    var name: String = ""
    for (word <- words) {
      if (word.contains(Constans.GIF)) name = word
    }
    return name
  }
}
