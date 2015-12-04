import scala.collection.mutable.ArrayBuffer

object Hello {
  def main(args: Array[String]): Unit = {

  println("hello, Activator?")
  }
}

object CSVDemo3 extends App {

  // each row is an array of strings (the columns in the csv file)
  val rows = ArrayBuffer[Array[String]]()

  // (1) read the csv data
  using(io.Source.fromFile("sample_data.csv")) { source =>
    for (line <- source.getLines) {
      rows += line.split(",").map(_.trim)
    }
  }

  // (2) print the results
  for (row <- rows) {
    println(s"${row(0)}|${row(1)}|${row(2)}|${row(3)}|${row(4)}|${row(5)}|${row(6)}|${row(7)}|${row(8)}|${row(9)}|${row(10)}|${row(11)}|${row(12)}|${row(13)}|${row(14)}|${row(15)}|${row(16)}|${row(17)}|${row(18)}|${row(19)}|${row(20)}|${row(21)}|${row(22)}")
  }

  def using[A <: { def close(): Unit }, B](resource: A)(f: A => B): B =
    try {
      f(resource)
    } finally {
      resource.close()
    }
}