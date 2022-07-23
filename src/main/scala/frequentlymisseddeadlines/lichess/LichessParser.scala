package frequentlymisseddeadlines.lichess

import frequentlymisseddeadlines.lichess.parser.PGNParser
import org.apache.spark.sql.SparkSession

// $COVERAGE-OFF$
object LichessParser {
  def main(args: Array[String]) {
    val spark = SparkSession.builder.appName("Lichess parser").getOrCreate()

    val games = spark.sparkContext.wholeTextFiles(args(0) + "/*.pgn")

    val flatten = games.flatMap{
      case (_, content) => content.split("\n\n\\[")
        .map(s => if (s.startsWith("\\[")) s else "\\[" + s)
        .map(PGNParser.parse)
        .groupBy(_.meta.opening)
    }.sortBy(_._2.size)

    println(flatten.take(5))
    spark.stop()
  }
}
// $COVERAGE-ON$