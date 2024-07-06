from pyspark.sql import SparkSession
someFile = "dummy"
# the above file is under your pythonProject folder
spark = SparkSession.builder.appName("SimpleApp").getOrCreate()
print(spark.read.text(someFile).count())
