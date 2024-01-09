package log

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.log4j.Level
import org.apache.log4j.Logger

object LogProcessing extends App {
  Logger.getLogger("org").setLevel(Level.OFF)
  val conf = new SparkConf().setAppName("logs").setMaster("local[2]")
  val sc = new SparkContext(conf)
  // Logs
  val julyFirstLogs = sc.textFile("data/nasa_19950701.tsv")
  val augustFirstLogs = sc.textFile("data/nasa_19950801.tsv")
  //Union Example
  val aggregatedLogLines = julyFirstLogs.union(augustFirstLogs)
  val cleanLogLines = aggregatedLogLines.filter(line => isNotHeader(line))
  cleanLogLines.saveAsTextFile("output/nasa_logs_all_hosts.csv")
  // Statistics Sample
  val sample = aggregatedLogLines.sample(withReplacement = true, fraction = 0.1)
  sample.saveAsTextFile("output/sample_nasa_logs.csv")
  // Intersection
  val intersectionLogLines = julyFirstLogs.intersection(augustFirstLogs)
  val cleanedHostIntersection = intersectionLogLines.filter(line => isNotHeader(line))
  cleanedHostIntersection.saveAsTextFile("output/nasa_logs_same_hosts.csv")

  def isNotHeader(line: String): Boolean = !(line.startsWith("host") && line.contains("bytes"))

}