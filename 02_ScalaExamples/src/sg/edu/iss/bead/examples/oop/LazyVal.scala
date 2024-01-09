package sg.edu.iss.bead.examples.oop

import scala.io.Source._

object ReadTextFile extends App {
  lazy val fileContents = fromFile(System.getProperty("user.dir") + "/testFile.txt").getLines
  println(fileContents.toString())
}