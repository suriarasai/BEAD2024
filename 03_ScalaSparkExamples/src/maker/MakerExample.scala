package maker

import org.apache.log4j.{ Level, Logger }
import org.apache.spark.{ SparkConf, SparkContext }

import scala.io.Source
import utils.Util

object MakerExample {

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.OFF)
    val conf = new SparkConf().setAppName("simple").setMaster("local[2]")
    val sc = new SparkContext(conf)
    //Broadcast Example
    val postCodeMap = sc.broadcast(loadPostCodeMap())
    val makerSpaceRdd = sc.textFile("data/uk-makerspaces-identifiable-data.csv")
    val regions = makerSpaceRdd
      .filter(line => line.split(Util.COMMA_DELIMITER, -1)(0) != "Timestamp")
      .filter(line => getPostPrefix(line).isDefined)
      .map(line => postCodeMap.value.getOrElse(getPostPrefix(line).get, "Unknown"))
    for ((region, count) <- regions.countByValue()) println(region + " : " + count)   
  }

  def getPostPrefix(line: String): Option[String] = {
    val splits = line.split(Util.COMMA_DELIMITER, -1)
    val postcode = splits(4)
    if (postcode.isEmpty) None else Some(postcode.split(" ")(0))
  }

  def loadPostCodeMap(): Map[String, String] = {
    Source.fromFile("data/uk-postcode.csv").getLines.map(line => {
      val splits = line.split(Util.COMMA_DELIMITER, -1)
      splits(0) -> splits(7)
    }).toMap
  }
}