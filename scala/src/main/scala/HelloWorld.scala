import scala.math.random
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import com.datastax.spark.connector._
import org.apache.spark.sql.cassandra._

object SparkPi {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("SparkApp")
    val sc = new SparkContext(conf)
    val spark = SparkSession.builder.appName("Simple Application").getOrCreate()
    val collection = sc.parallelize(Seq(("key3", 3), ("key4", 4)))
    var rdd = sc.cassandraTable("test", "kv")
    println(rdd.count)
    println(rdd.first)
    println(rdd.map(_.getInt("value")).sum)
    collection.saveToCassandra("test", "kv", SomeColumns("key", "value"))
    rdd = sc.cassandraTable("test", "kv")
    println(rdd.count)
    println(rdd.first)
    println(rdd.map(_.getInt("value")).sum)
    val df = spark.read.format("org.apache.spark.sql.cassandra")
    .options(Map(
      "table" -> "measures",
      "keyspace" -> "dev")).load()
    df.describe().show()
    sc.stop()
  }
}
