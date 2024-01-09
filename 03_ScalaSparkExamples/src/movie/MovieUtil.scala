package movie

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, SparkSession}


object MovieUtil {
  val spConfig = (new SparkConf).setMaster("local").setAppName("SparkApp")

  val spark = SparkSession
    .builder().master("local")
    .appName("Spark 2.0.0")
    .config("spark.some.config.option", "some-value")
    .getOrCreate()

  val sc = spark.sparkContext

  val PATH_MOVIES = "data/movielens/u.item"
  val PATH_USERS =  "data/movielens/u.user"

  def getMovieData() : RDD[String] = {
    return null
  }

  def getMovieDataDF() : DataFrame = {

    //1|Toy Story (1995)|01-Jan-1995||http://us.imdb.com/M/title-exact?Toy%20Story%20(1995)
    // |0|0|0|1|1|1|0|0|0|0|0|0|0|0|0|0|0|0|0
    val customSchema = StructType(Array(
      StructField("id", StringType, true),
      StructField("name", StringType, true),
      StructField("date", StringType, true),
      StructField("url", StringType, true)));
    val movieDf = spark.read.format("com.databricks.spark.csv")
      .option("delimiter", "|").schema(customSchema)
      .load(PATH_MOVIES)
    return movieDf
  }



  def numMovies() : Long = {
    return getMovieData().count()
  }

  def movieFields() : RDD[Array[String]] = {
    return this.getMovieData().map(lines =>  lines.split("\\|"))
  }

  def mean( x:Array[Int]) : Int = {
    return x.sum/x.length
  }
  def getMovieAges(movie_data : RDD[String]) : scala.collection.Map[Int, Long] = {
    val movie_fields = movie_data.map(lines =>  lines.split("\\|"))
    val years = movie_fields.map( field => field(2)).map( x => convertYear(x))
    val years_filtered = years.filter(x => (x != 1900) )
    val movie_ages = years_filtered.map(yr =>  (1998 - yr) ).countByValue()
    return movie_ages
  }

  def getMovieAgesDataFrame(movieData: DataFrame) : scala.collection.Map[Int, Long] = {
    
    print(this.getMovieDataDF().first.getClass)

    return null
  }



  def convertYear( x:String) : Int = {
    try
      return x.takeRight(4).toInt
    catch {
      case e: Exception => println("exception caught: " + e + " Returning 1900");
        return 1900
    }
  }

  def getUserData() : RDD[String] = {
    var user_data = MovieUtil.spark.sparkContext.textFile("data/movielens/u.user")
    return user_data
  }

  def getUserFields() : RDD[Array[String]] = {
    val user_data = this.getUserData()
    val user_fields = user_data.map(l => l.split(","))
    return user_fields
  }

  def getUserFieldDataFrame() : DataFrame = {
    val customSchema = StructType(Array(
      StructField("no", IntegerType, true),
      StructField("age", StringType, true),
      StructField("gender", StringType, true),
      StructField("occupation", StringType, true),
      StructField("zipCode", StringType, true)));
    val spConfig = (new SparkConf).setMaster("local").setAppName("SparkApp")
    val spark = SparkSession
      .builder()
      .appName("SparkSessionZipsExample").config(spConfig)
      .getOrCreate()

    val user_df = spark.read.format("com.databricks.spark.csv")
      .option("delimiter", "|").schema(customSchema)
      .load(PATH_USERS)
    return user_df
  }


  def convert(x: String) : Integer = {
    return (1998 - x.toInt)
  }

  def convert(x: Integer) : Integer = {
    return (1998 - x)
  }
}

