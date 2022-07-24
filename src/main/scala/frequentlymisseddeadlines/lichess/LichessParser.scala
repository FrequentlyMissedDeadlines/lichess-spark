package frequentlymisseddeadlines.lichess

import frequentlymisseddeadlines.lichess.analyzer.BlunderFinder
import frequentlymisseddeadlines.lichess.parser.GameParser
import org.apache.spark.sql.SparkSession

// $COVERAGE-OFF$
object LichessParser {
  def main(args: Array[String]) {
    val spark = SparkSession.builder.appName("Lichess parser").getOrCreate()

    val games = spark.sparkContext.wholeTextFiles(args(0) + "/*.pgn").flatMap{
      case (_, content) => content.split("\n\n\\[")
        .map(s => if (s.startsWith("\\[")) s else "\\[" + s)
        .map(GameParser.parse)
    }

    val openings = games.map(a => (a.meta.opening, a)).groupByKey()
    val events = games.map(a => (a.meta.event, a)).groupByKey()
    val evaluated = games.filter(g => !g.evals.isEmpty)
    val blunders = evaluated.flatMap(BlunderFinder.getBlunders)
    val blundersPerOpening = blunders.filter(b => b.move.toInt <= 20).map(b => (List(b.game.opening, b.move).mkString(","),b)).groupByKey()

    openings.sortBy(_._2.size, false).map(a => a._1 + "," + a._2.size).saveAsTextFile("output/openings")
    events.sortBy(_._2.size, false).map(a => a._1 + "," + a._2.size).saveAsTextFile("output/events")
    evaluated.map(_.toCSV).saveAsTextFile("output/evaluated")
    blunders.map(_.toCSV).saveAsTextFile("output/blunders")
    blundersPerOpening.sortBy(_._2.size, false).map(a => List(a._1,a._2.size, a._2.take(1).toList(0).game.url).mkString(",")).saveAsTextFile("output/blunders_by_frequency")
    spark.stop()
  }
}
// $COVERAGE-ON$