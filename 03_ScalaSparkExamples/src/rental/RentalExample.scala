package rental

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.log4j.Level
import org.apache.log4j.Logger

object RentalExample extends App {
  
    Logger.getLogger("org").setLevel(Level.ERROR)
    val conf = new SparkConf().setAppName("averageHousePriceSolution").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val lines = sc.textFile("data/RealEstate.csv")
    val cleanedLines = lines.filter(line => !line.contains("Bedrooms"))
    val housePricePairRdd = cleanedLines.map(line => (line.split(",")(3), (1, line.split(",")(2).toDouble)))
    val housePriceTotal = housePricePairRdd.reduceByKey((x, y) => (x._1 + y._1, x._2 + y._2))
    println("housePriceTotal: ")
    for ((bedroom, total) <- housePriceTotal.collect()) println(bedroom + " : " + total)
    val housePriceAvg = housePriceTotal.mapValues(avgCount => avgCount._2 / avgCount._1)
    println("housePriceAvg: ")
    for ((bedroom, avg) <- housePriceAvg.collect()) println(bedroom + " : " + avg)
}