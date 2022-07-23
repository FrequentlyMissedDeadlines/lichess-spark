package frequentlymisseddeadlines.lichess.parser

object PGNParser {
  def parse(pgn: String): PGN = {
    val regexW = "White \"(.*)\"".r
    val regexB = "Black \"(.*)\"".r
    val regexWelo = "WhiteElo \"(.*)\"".r
    val regexBelo = "BlackElo \"(.*)\"".r
    val regexOpening = "Opening \"(.*)\"".r
    val white = regexW.findFirstMatchIn(pgn).map(_.group(1)).getOrElse("")
    val black = regexB.findFirstMatchIn(pgn).map(_.group(1)).getOrElse("")
    val whiteElo = regexWelo.findFirstMatchIn(pgn).map(_.group(1)).getOrElse("1500")
    val blackElo = regexBelo.findFirstMatchIn(pgn).map(_.group(1)).getOrElse("1500")
    val opening = regexOpening.findFirstMatchIn(pgn).map(_.group(1)).getOrElse("")
    PGN(
      meta = GameMeta(
        white = white,
        black = black,
        whiteElo = whiteElo.toInt,
        blackElo = blackElo.toInt,
        opening = opening
      ),
      moves = Moves()
    )
  }
}
