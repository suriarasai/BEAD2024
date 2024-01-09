name := "03_ScalaSparkExamples"

version := "1.0"

scalaVersion := "2.11.12"

val sparkVersion = "2.4.7"


resolvers ++= Seq(
  "apache-snapshots" at "http://repository.apache.org/snapshots/"
)

libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "5.1.+",
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.spark" %% "spark-mllib" % sparkVersion,
  "org.apache.spark" %% "spark-streaming" % sparkVersion,
  "org.apache.spark" %% "spark-hive" % sparkVersion,
  "com.itextpdf" % "itextpdf" % "5.5.6",
  "org.jfree" % "jfreesvg" % "3.0",
  "com.databricks" % "spark-csv_2.11" % "1.4.0",
  "com.github.wookietreiber" % "scala-chart_2.11" % "0.5.0"
)