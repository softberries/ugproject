package test.java.ug.gda.pl

import org.junit._

import scala.ug.gda.pl.engine.Constans

/**
 * Created by Maciek on 2015-12-05.
 */
class SomeTests {

  @Before
  var constans = Constans

  @Test
  def test = assert(true)

  @Test
  def pathTest = assert(constans.source.length > 0)

}
