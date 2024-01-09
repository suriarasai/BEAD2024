package basic

import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.storage.StorageLevel
import utils.Util
import org.apache.spark.rdd.RDD.rddToPairRDDFunctions

object SimpleOperations extends App {

  Logger.getLogger("org").setLevel(Level.OFF)
  val conf = new SparkConf().setAppName("simple").setMaster("local[2]")
  val sc = new SparkContext(conf)
  // Take Example
  val inputWords = List("spark", "hadoop", "spark", "hive", "pig", "cassandra", "hadoop")
  val wordRdd = sc.parallelize(inputWords)

  val words = wordRdd.take(3)
  for (word <- words) println(word)

  // Persistence
  val inputIntegers = List(1, 2, 3, 4, 5)
  val integerRdd = sc.parallelize(inputIntegers)
  integerRdd.persist(StorageLevel.MEMORY_ONLY)
  integerRdd.reduce((x, y) => x * y)
  println(integerRdd.count())

  // Collect Operator
  val inputWords1 = List("spark", "hadoop", "spark", "hive", "pig", "cassandra", "hadoop")
  val wordRdd1 = sc.parallelize(inputWords)
  val words1 = wordRdd1.collect()
  for (word1 <- words1) println(word1)

  // Count Example
  val poems = sc.textFile("data/PoemsByKeats.txt")
  val lineLengths = poems.count()
  println(s"Total Lines : $lineLengths")

  //FlatMapExample
  val wc = poems.flatMap(_.split(Util.COMMA_DELIMITER)).collect().size
  println(s"Total Words : $wc")

  // Reduce Example
  val inputIntegers1 = List(1, 2, 3, 4, 5)
  val integerRdd1 = sc.parallelize(inputIntegers1)

  val product = integerRdd1.reduce((x, y) => x * y)
  println("product is :" + product)

    
  val ordersRDD = sc.textFile("data/orders.txt")

  val orderItemssRDD = sc.textFile("data/order_items.txt")

  val ordersMap = ordersRDD.map(order => {
    (order.split(",")(0).toInt, order)
  })

  ordersMap.take(10).foreach(println)
  val orderItemsMap = orderItemssRDD.map(orderItem =>{
    val oi = orderItem.split(",")
    (oi(1).toInt, orderItem)
  })

  orderItemsMap.take(10).foreach(println)

  //Get all the orders which do not have corresponding entries in order items
  println("left outer join")
  val leftOuterJoin = ordersMap.leftOuterJoin(orderItemsMap)
  leftOuterJoin.take(10).foreach(println)
  //(31037,(31037,2014-02-02 00:00:00.0,8301,COMPLETE,None))
  val leftOuterJoinFilter = leftOuterJoin.filter(order => order._2._2 == None)
  leftOuterJoinFilter.take(10).foreach(println)

  val ordersWithNoOrderItems = leftOuterJoinFilter.map(order => order._2._1)

  ordersWithNoOrderItems.foreach(println)

  println("right outer join")

  val rightOuterJoin = orderItemsMap.rightOuterJoin(ordersMap)
  rightOuterJoin.take(10).foreach(println)
  //(19021,(None,19021,2013-11-20 00:00:00.0,9532,PENDING_PAYMENT))
  val rightOuterJoinFilter = rightOuterJoin.filter(order => order._2._1 == None)
  rightOuterJoin.take(10).foreach(println)

  val ordersWithNoOrderItems2 = rightOuterJoinFilter.map(order => order._2._2)

  ordersWithNoOrderItems2.take(10).foreach(println)

}