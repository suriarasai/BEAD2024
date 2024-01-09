package product

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.log4j.Level
import org.apache.log4j.Logger

object MaxPrice extends App {
  
  Logger.getLogger("org").setLevel(Level.OFF)
  val conf = new SparkConf().setAppName("Max Price").setMaster("local")
  val sc = new SparkContext(conf)
  sc.textFile("data/tableHistorical.csv")
    .map(_.split(","))
    .map(rec => ((rec(0).split("-"))(0).toInt, rec(1).toFloat))
    .reduceByKey((a, b) => Math.max(a, b))
    .saveAsTextFile("output/maxPrice.txt")

}