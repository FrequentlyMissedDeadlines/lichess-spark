package frequentlymisseddeadlines.lichess.analyzer

import frequentlymisseddeadlines.lichess.analyzer.BlunderType.BlunderType
import frequentlymisseddeadlines.lichess.parser.GameMeta

object BlunderType extends Enumeration {
  type BlunderType = Value
  val MATERIAL, MATE, MATE_LOSS = Value
}

case class Blunder(game: GameMeta, move: String, blunderType: BlunderType) {
  def toCSV(): String = {
    List(game.toCSV(), move, blunderType).mkString(",")
  }
}
