package classification

import org.apache.spark.SparkConf


object SparkCommonUtils {

  def createSparkConf(appName: String): SparkConf = {
     new SparkConf().setAppName(appName).setMaster(SparkConstants.SparkMaster)
  }

}
