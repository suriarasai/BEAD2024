package airport

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import utils.Util
import org.apache.log4j.Level
import org.apache.log4j.Logger

object AirportStats extends App {

  Logger.getLogger("org").setLevel(Level.OFF)
  val conf = new SparkConf().setAppName("airports").setMaster("local[2]")
  val sc = new SparkContext(conf)

  val airports = sc.textFile("data/airports.text")
  val airportsInUSA = airports.filter(line => line.split(Util.COMMA_DELIMITER)(6).toFloat > 40)

  val airportsNameAndCityNames = airportsInUSA.map(line => {
    val splits = line.split(Util.COMMA_DELIMITER)
    splits(1) + ", " + splits(6)
  })
  airportsNameAndCityNames.saveAsTextFile("output/airports_by_latitude.txt")
  
  // Map Example
  val lineLengths = airports.count()
  println(s"Total Lines : $lineLengths")
  
  //FlatMapExample
  val wc = airports.flatMap(_.split(Util.COMMA_DELIMITER)).collect().size
  println(s"Total Words : $wc")
  
  val airportsInCanada = airports.filter(line => line.split(Util.COMMA_DELIMITER)(3) == "\"Canada\"")

    val airportsCanadaNameAndCityNames = airportsInCanada.map(line => {
      val splits = line.split(Util.COMMA_DELIMITER)
      splits(1) + ", " + splits(2) + ", " + splits(3)
    })
    airportsCanadaNameAndCityNames.saveAsTextFile("output/airports_in_canada.text")
 
}