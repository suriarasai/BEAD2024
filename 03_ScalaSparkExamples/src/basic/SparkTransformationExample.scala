

package basic

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.log4j.Level
import org.apache.log4j.Logger

object SparkTransformationExample {
  def main(args: Array[String]) {
    Logger.getLogger("org").setLevel(Level.OFF)
    val conf = new SparkConf
    conf.setMaster("local[2]")
      .setAppName("SparkTransformationExample")
    val sc = new SparkContext(conf)
    val baseRdd1 =
      sc.parallelize(Array("hello", "hi", "suria", "big", "data",
        "hub", "hub", "hi"), 1)
    val baseRdd2 =
      sc.parallelize(Array("hey", "daniel",
        "mohammad", "prateek"), 1)
    val baseRdd3 = sc.parallelize(Array(1, 2, 3, 4), 2)
    val sampledRdd = baseRdd1.sample(false, 0.5)
    val unionRdd = baseRdd1.union(baseRdd2).repartition(1)
    val intersectionRdd = baseRdd1.intersection(baseRdd2)
    val distinctRdd = baseRdd1.distinct.repartition(1)
    val subtractRdd = baseRdd1.subtract(baseRdd2)
    val cartesianRdd = sampledRdd.cartesian(baseRdd2)
    val reducedValue = baseRdd3.reduce((a, b) => a + b)
    val collectedRdd = distinctRdd.collect
    collectedRdd.foreach(println)
    val count = distinctRdd.count
    val first = distinctRdd.first
    println("Count is..." + count);
    println("First Element is..." + first)
    val takeValues = distinctRdd.take(3)
    val takeSample = distinctRdd.takeSample(false, 2)
    val takeOrdered = distinctRdd.takeOrdered(2)
    takeValues.foreach(println)
    println("Take Sample Values..")
    takeSample.foreach(println)
    val foldResult = distinctRdd.fold("<>")((a, b) => a + b)
    println(foldResult)
  }
} 
