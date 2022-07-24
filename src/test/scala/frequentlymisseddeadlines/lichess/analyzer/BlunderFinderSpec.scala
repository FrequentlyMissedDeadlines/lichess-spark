package frequentlymisseddeadlines.lichess.analyzer

import frequentlymisseddeadlines.lichess.parser.EventTypes.UNKNOWN
import frequentlymisseddeadlines.lichess.parser.{Eval, Game, GameMeta, Moves}
import org.specs2.mutable.Specification

class BlunderFinderSpec extends Specification {
  val meta = GameMeta("white", "black", 1000, 1000, "opening", UNKNOWN, "url")
  "BlunderFinder" should {
    "identify material loss as blunder" in {
      val blunders = BlunderFinder.getBlunders(Game(
        meta = meta,
        moves = Moves(),
        evals = List(
          Eval(0.0, None),
          Eval(2.8, None)
        )
      ))

      blunders must beEqualTo(List(
        Blunder(meta, "2", BlunderType.MATERIAL)
      ))
    }

    "identify mate loss as blunder" in {
      val blunders = BlunderFinder.getBlunders(Game(
        meta = meta,
        moves = Moves(),
        evals = List(
          Eval(0.0, Some(1)),
          Eval(5.0, None)
        )
      ))

      blunders must beEqualTo(List(
        Blunder(meta, "2", BlunderType.MATE_LOSS)
      ))
    }

    "identify mate as blunder" in {
      val blunders = BlunderFinder.getBlunders(Game(
        meta = meta,
        moves = Moves(),
        evals = List(
          Eval(5.0, None),
          Eval(0.0, Some(1))
        )
      ))

      blunders must beEqualTo(List(
        Blunder(meta, "2", BlunderType.MATE)
      ))
    }

    "ignore minor material loss" in {
      val blunders = BlunderFinder.getBlunders(Game(
        meta = meta,
        moves = Moves(),
        evals = List(
          Eval(0.0, None),
          Eval(2.7, None),
          Eval(0.0, None)
        )
      ))

      blunders must beEqualTo(Nil)
    }

    "ignore material loss when the game is won" in {
      val blunders = BlunderFinder.getBlunders(Game(
        meta = meta,
        moves = Moves(),
        evals = List(
          Eval(50.0, None),
          Eval(53.0, None),
          Eval(90.0, None)
        )
      ))

      blunders must beEqualTo(Nil)
    }

    "ignore mate loss when the game is won" in {
      val blunders = BlunderFinder.getBlunders(Game(
        meta = meta,
        moves = Moves(),
        evals = List(
          Eval(0.0, Some(2)),
          Eval(53.0, None)
        )
      ))

      blunders must beEqualTo(Nil)
    }
  }
}
