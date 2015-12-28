package scalding
import java.io._

object TestData {


  def tempFile(prefix: String = "activator-scalding"): File = {
    val file = File.createTempFile(prefix, ".txt")
    file.deleteOnExit()
    file
  }

  def writeTempFile(prefix: String, text: String): File = {
    val file = tempFile(prefix)
    val writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
    writer.write(text)
    writer.close()
    file
	}

  val activatorText = """
Typesafe Activator is a browser-based or command-line tool that helps developers get started with the Typesafe Reactive Platform.

A new addition to the Typesafe Reactive Platform is Typesafe Activator, a unique, browser-based tool that helps developers get started with Typesafe technologies quickly and easily. Activator is a hub for developers wanting to build Reactive applications. Unlike previous developer-focused offerings that are delivered simply via a website, Activator breaks new ground by delivering a rich application directly to the desktop. Activator updates in real-time with new content from Typesafe and value-add third parties, helping developers engage and adopt Typesafe technologies in an entirely frictionless manner.

Getting started is a snap; just download, extract and run the executable to start building applications immediately via the easy to use wizard-based interface. Common development patterns are presented through reusable templates that are linked to in-context tutorials, which explain step-by-step exactly how things work. The Activator environment supports each stage of the application development lifecycle: Code, Compile, Test and Run. At the appropriate time, Activator can generate fully fledged projects for the leading IDEs so that application development can continue in these environments.

The rich developer content in Typesafe Activator is dynamic and customizable. New templates are published regularly on the Typesafe website, and anyone can contribute new templates!"""

	val diggJsonText =
  """
    | some text {} strange chars but the url is between http://i.imgur.com/gKoR38B.png
    | on another line one more http://i.imgur.com/gKoR38B.png
    | and one more again
    | http://i.imgur.com/gKoR38B.png the last one should not be counted: http://i.imgur.com/gKoR38B.png
    | Now lets do two time the second url http://i.imgur.com/e1rmqnp.gif
    | one more line of course http://i.imgur.com/e1rmqnp.gif and here we go
    | The last url should be there just once http://i.imgur.com/vqMOTbo.gif
    | and thats it
  """.stripMargin

  val sampleJsonUrlCountExpected = List(
    ("http://i.imgur.com/gKoR38B.png", 3),
    ("http://i.imgur.com/e1rmqnp.gif", 2),
    ("http://i.imgur.com/vqMOTbo.gif", 1))
}