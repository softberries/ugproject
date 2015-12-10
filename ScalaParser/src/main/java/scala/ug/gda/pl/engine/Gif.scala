package scala.ug.gda.pl.engine

/**
 * Created by Maciek on 2015-12-07.
 */
case class Gif(name:String ,link:String ) {
  override def toString: String = {
    "\nname : "+name+ " link : "+link
  }
}
