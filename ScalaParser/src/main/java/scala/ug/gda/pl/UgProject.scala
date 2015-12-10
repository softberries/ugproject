package scala.ug.gda.pl

import scala.ug.gda.pl.engine.{Gif, EngineComponents}

/**
 * Created by Maciek on 2015-12-05.
 */
object UgProject {

  def main(args: Array[String]) {

    val engine = EngineComponents
    val gifList: List[Gif] = engine.generateGifsList()
    engine.printGifList(gifList)

  }
}