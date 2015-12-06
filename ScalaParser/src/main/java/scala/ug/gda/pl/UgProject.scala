package scala.ug.gda.pl

import scala.ug.gda.pl.engine.EngineComponents

/**
 * Created by Maciek on 2015-12-05.
 */
object UgProject {

  def main(args: Array[String]) {

    var engine = EngineComponents
    val gifList: List[String] = engine.generateGifsList()
    engine.printGifList(gifList)

  }
}