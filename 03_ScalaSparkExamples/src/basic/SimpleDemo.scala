package basic

import org.apache.spark.sql.SparkSession
import org.apache.log4j.Level
import org.apache.log4j.Logger

object SimpleDemo extends App {
    Logger.getLogger("org").setLevel(Level.OFF)
    val logFile = "data/readme.txt" // Should be some file on your system
    val spark = SparkSession.builder.appName(getClass.getSimpleName).master("local[2]").getOrCreate()
    val logData = spark.read.textFile(logFile).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    println(s"Lines with a: $numAs, Lines with b: $numBs")
    spark.stop()
}