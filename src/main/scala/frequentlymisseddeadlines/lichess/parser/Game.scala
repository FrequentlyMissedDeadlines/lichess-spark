package frequentlymisseddeadlines.lichess.parser

case class Game(meta: GameMeta, moves: Moves, evals: List[Eval]) {
  def toCSV(): String = {
    List(meta.event, meta.white, meta.black, meta.whiteElo, meta.blackElo, meta.opening, meta.url, moves.toString, evals.mkString("^")).mkString(",")
  }
}
