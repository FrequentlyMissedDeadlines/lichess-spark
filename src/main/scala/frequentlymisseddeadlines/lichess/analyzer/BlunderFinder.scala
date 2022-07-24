package frequentlymisseddeadlines.lichess.analyzer

import frequentlymisseddeadlines.lichess.parser.Game

object BlunderFinder {
  val BLUNDER_THRESHOLD = 2.8
  def getBlunders(game: Game): List[Blunder] = {
    (game.evals zip game.evals.drop(1)).zipWithIndex.flatMap { case (element, index) => element match {
      case (e1, e2) => if (e1.mateIn.isEmpty != e2.mateIn.isEmpty && Math.abs(e1.eval) < 9 && Math.abs(e2.eval) < 9) {
        Some(Blunder(game.meta, (index+2) + "", if (e1.mateIn.isEmpty) BlunderType.MATE else BlunderType.MATE_LOSS))
      } else if (e1.mateIn.isEmpty == e2.mateIn.isEmpty && Math.abs(e1.eval - e2.eval) >= BLUNDER_THRESHOLD && (Math.abs(e1.eval) < 9 || Math.abs(e2.eval) < 9)) {
        Some(Blunder(game.meta, "" + (index+2), BlunderType.MATERIAL))
      } else {
        None
      }
    }}
  }
}
