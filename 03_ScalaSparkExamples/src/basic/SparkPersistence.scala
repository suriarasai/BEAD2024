package basic

import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.storage.StorageLevel
import org.apache.spark.rdd.RDD.rddToPairRDDFunctions

object SparkPersistence extends App {

  Logger.getLogger("org").setLevel(Level.OFF)

  val conf = new SparkConf().setAppName("Persisting RDD").setMaster("local[2]")
  val sc = new SparkContext(conf)

  val inputRdd =
    sc.parallelize(Array("this,is,a,ball", "it,is,a,cat", "Pragathi,is,in,the,temple "))
  val wordsRdd = inputRdd.flatMap(record => record.split(","))
  val wordLengthPairs = wordsRdd.map(word => (word, word.length))
  val wordPairs = wordsRdd.map(word => (word, 1))
  val reducedWordCountRdd = wordPairs.reduceByKey((x, y) => x + y)
  val filteredWordLengthPairs = wordLengthPairs.filter {
    case (word, length) =>
      length >= 3
  }
  reducedWordCountRdd.cache()
  val joinedRdd = reducedWordCountRdd.join(filteredWordLengthPairs)
  joinedRdd.persist(StorageLevel.MEMORY_AND_DISK)
  val wordPairsCount = reducedWordCountRdd.count
  val wordPairsCollection = reducedWordCountRdd.take(10)
  val joinedRddCount = joinedRdd.count
  val joinedPairs = joinedRdd.collect()
  reducedWordCountRdd.unpersist()
  joinedRdd.unpersist()

}