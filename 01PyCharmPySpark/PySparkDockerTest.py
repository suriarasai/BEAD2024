from pyspark.sql import SparkSession
# Initialize SparkSession
spark = SparkSession.builder \
    .appName("HelloWorld") \
    .master("spark://localhost:7077") \
    .config("spark.executor.memory", "4g") \
    .config("spark.executor.cores", "1") \
    .config("spark.driver.memory", "4g") \
    .config("spark.driver.cores", "1") \
    .getOrCreate()
print(spark.sparkContext.getConf().getAll())
# Create a RDD
data = [1, 2, 3, 4, 5]
distData = spark.sparkContext.parallelize(data)
print(distData.collect())