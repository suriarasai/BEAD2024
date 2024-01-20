from pyspark.sql import SparkSession
spark = SparkSession.builder.appName('read JSON files').getOrCreate()


df = spark.read.format('json') \
                .option("inferSchema","true") \
                .option("header","true") \
                .option("sep",";") \
                .load("people.json")
df.show()
df.printSchema()

df2 = spark.read.json("people.json")
df2.show()

