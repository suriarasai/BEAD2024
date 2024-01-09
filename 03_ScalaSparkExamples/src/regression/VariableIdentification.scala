package regression

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.types.DoubleType

object VariableIdentification {
  
   def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local[2]")
      .setAppName("Sales_Prediction")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    val sqlContext = new SQLContext(sc)
    
    //Loading data
    val sales_data_train =
      sqlContext.read.format("com.databricks.spark.csv")
        .option("header", "true")
        .option("inferSchema", "true")
        .load("/home/cloudera/git/BEAD2020/06-SparkML/data/regression/Sales_Train.csv")
    val sales_data_test =
      sqlContext.read.format("com.databricks.spark.csv")
        .option("header", "true")
        .option("inferSchema", "true")
        .load("/home/cloudera/git/BEAD2020/06-SparkML/data/regression/Sales_Test.csv")
    val sales_data_union = sales_data_train.unionAll(sales_data_test)
    val sales_data = sales_data_union.withColumn(
      "Item_Outlet_Sales",
      sales_data_union.col("Item_Outlet_Sales").cast(DoubleType))
    sales_data.show(5)
  }
  
}
