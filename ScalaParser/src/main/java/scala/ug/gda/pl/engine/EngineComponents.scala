package scala.ug.gda.pl.engine

import java.io.{FileReader, BufferedReader}

import scala.collection.mutable.ListBuffer
import scala.reflect.internal.util.StringOps

/**
 * Created by Maciek on 2015-12-05.
 */
object EngineComponents {

  def printGifList(gifList: List[String]) {
    println("START")
    gifList.foreach(gif => println(gif))
    println("\nEND")
  }

  def generateGifsList(): List[String] = {
    val bufferedReader = new BufferedReader(new FileReader(Constans.source))
    var line: String = ""

    val listTemp = new ListBuffer[String]()
    while ( {
      line = bufferedReader.readLine(); line != null
    }) {
      val wordList: List[String] = StringOps.words(line)
      wordList.foreach(word => filter(word, listTemp))
    }
    return listTemp.toList
  }


  def filter(word: String, listOfGift: ListBuffer[String]) {
    if (word.contains(Constans.GIF)) {
      listOfGift += (word)
    }
  }

}
