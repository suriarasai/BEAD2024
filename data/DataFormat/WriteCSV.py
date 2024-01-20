from pyspark.sql import SparkSession
spark = SparkSession.builder.appName('Write CSV files').getOrCreate()
from pyspark.sql.types import StructType, StructField, StringType,IntegerType

# Write csv file
df = spark.read.format('csv') \
                .option("inferSchema","true") \
                .option("header","true") \
                .option("sep",";") \
                .load("people.csv")

# Show result
df.show()
# Print schema
df.printSchema

#  Write DataFrame into local file path
df.write.format("csv") \
                .option("header","true") \
                .option("sep",";")  \
                .mode("overwrite") \
                .save("people2")