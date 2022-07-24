package frequentlymisseddeadlines.lichess.parser

object GameParser {
  val DEFAULT_ELO = 1500

  val regexW = "White \"(.*)\"".r
  val regexB = "Black \"(.*)\"".r
  val regexWelo = "WhiteElo \"(.*)\"".r
  val regexBelo = "BlackElo \"(.*)\"".r
  val regexOpening = "Opening \"(.*)\"".r
  val regexEvent = "Event \"(.*)\"".r
  val regexMoves = "^1. (.*)$".r
  val regexEval = "\\{ \\[%eval ([+\\-0-9\\.#]*)\\] \\}".r
  val regexSite = "Site \"(.*)\"".r

  def parse(pgn: String): Game = {

    val white = regexW.findFirstMatchIn(pgn).map(_.group(1)).getOrElse("")
    val black = regexB.findFirstMatchIn(pgn).map(_.group(1)).getOrElse("")
    val whiteElo = getEloFromOptionString(regexWelo.findFirstMatchIn(pgn).map(_.group(1)))
    val blackElo = getEloFromOptionString(regexBelo.findFirstMatchIn(pgn).map(_.group(1)))
    val opening = regexOpening.findFirstMatchIn(pgn).map(_.group(1)).getOrElse("")
    val event = regexEvent.findFirstMatchIn(pgn).map(_.group(1)).map(EventTypes.fromString).getOrElse(EventTypes.UNKNOWN)
    val evals = regexEval.findAllMatchIn(pgn)
      .flatMap(_.subgroups)
      .map(Eval.fromString)
      .toList
    val url = regexSite.findFirstMatchIn(pgn).map(_.group(1)).getOrElse("")
    Game(
      meta = GameMeta(
        white = white,
        black = black,
        whiteElo = whiteElo,
        blackElo = blackElo,
        opening = opening,
        event = event,
        url = url
      ),
      moves = Moves(),
      evals = evals
    )
  }

  def getEloFromOptionString(elo: Option[String]): Int = elo match {
    case Some(s) if s.endsWith("?") => getEloFromOptionString(Some(s.substring(0, s.length - 1)))
    case Some(s) => if (s.length >= 1) s.toInt else DEFAULT_ELO
    case _ => DEFAULT_ELO
  }
}
