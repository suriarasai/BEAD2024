package basic

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.log4j.Level
import org.apache.log4j.Logger


object CreateRDD extends App {
  Logger.getLogger("org").setLevel(Level.OFF)
  val conf = new SparkConf().setAppName("simple").setMaster("local[2]")
  val sc = new SparkContext(conf)
  // Collect Operator
  val inputWords = List("spark", "hadoop", "spark", "hive", "pig", "cassandra", "hadoop")
  val wordRdd = sc.parallelize(inputWords)
  val words = wordRdd.collect()
  for (word <- words) println(word)
  // Line Count Example
  val poems = sc.textFile("data/PoemsByKeats.txt")
  val lineLengths = poems.count()
  println(s"Total Lines : $lineLengths")
  // Word Count Example
  val wordCount = poems.flatMap(line=> line.split(" ") ).count()
  println(s"Total Words : $wordCount")
  // File Count Example
  val filesCount = sc.wholeTextFiles("data").count()
  println(s"Total Words : $filesCount")
}