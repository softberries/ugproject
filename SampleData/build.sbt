name := """sample-data"""

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies += "net.sf.opencsv" % "opencsv" % "2.3"
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.2"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.3.4"
libraryDependencies += "net.sf.opencsv" % "opencsv" % "2.3"

fork in run := true