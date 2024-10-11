from pyspark.sql import SparkSession

# Configure Spark to use S3A with MinIO
spark = SparkSession.builder \
    .appName("PySpark with MinIO") \
    .config("spark.hadoop.fs.s3a.endpoint", "http://minio:9000") \
    .config("spark.hadoop.fs.s3a.access.key", "minioadmin") \
    .config("spark.hadoop.fs.s3a.secret.key", "minioadmin") \
    .config("spark.hadoop.fs.s3a.impl", "org.apache.hadoop.fs.s3a.S3AFileSystem") \
    .config("spark.hadoop.fs.s3a.path.style.access", "true") \
    .config("spark.hadoop.fs.s3a.connection.ssl.enabled", "false") \
    .getOrCreate()

# Example: Reading from and writing to MinIO
df = spark.read.csv("s3a://mybucket/mydata.csv").option("header", "true").option("inferSchema", "true")
df.show()

df.write.csv("s3a://mybucket/output")
spark.stop()

