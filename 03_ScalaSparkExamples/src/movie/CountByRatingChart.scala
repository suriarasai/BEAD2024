package movie

import java.awt.Font

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.IntegerType
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.types.StructType
import org.jfree.chart.axis.CategoryLabelPositions

import scalax.chart.module.ChartFactories

object CountByRatingChart {

  def main(args: Array[String]) {

    val customSchema = StructType(Array(
      StructField("user_id", IntegerType, true),
      StructField("movie_id", IntegerType, true),
      StructField("rating", IntegerType, true),
      StructField("timestamp", IntegerType, true)))

    val spConfig = (new SparkConf).setMaster("local").setAppName("SparkApp")
    val spark = SparkSession
      .builder()
      .appName("SparkRatingData").config(spConfig)
      .getOrCreate()

    val rating_df = spark.read.format("com.databricks.spark.csv")
      .option("delimiter", "\t").schema(customSchema)
      .load("data/movielens/u.data")

    val rating_df_count = rating_df.groupBy("rating").count().sort("rating")
     rating_df_count.show()
    val rating_df_count_collection = rating_df_count.collect()

    val ds = new org.jfree.data.category.DefaultCategoryDataset
    val mx = scala.collection.immutable.ListMap()

    for (x <- 0 until rating_df_count_collection.length) {
      val occ = rating_df_count_collection(x)(0)
      val count = Integer.parseInt(rating_df_count_collection(x)(1).toString)
      ds.addValue(count, "UserAges", occ.toString)
    }
    val chart = ChartFactories.BarChart(ds)
    val font = new Font("Dialog", Font.PLAIN, 5);

    chart.peer.getCategoryPlot.getDomainAxis().
      setCategoryLabelPositions(CategoryLabelPositions.UP_90);
    chart.peer.getCategoryPlot.getDomainAxis.setLabelFont(font)
    chart.show()
    MovieUtil.sc.stop()
  }
}
