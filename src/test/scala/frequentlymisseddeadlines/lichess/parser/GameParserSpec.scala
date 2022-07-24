package frequentlymisseddeadlines.lichess.parser

import org.specs2.mutable.Specification

object GameParserSpec extends Specification {

  "PGN Parser " should {
    val pgn = GameParser.parse("[Event \"Rated Bullet game\"]\n[Site \"https://lichess.org/QSgawA0K\"]\n[White \"ShahinMohammad\"]\n[Black \"Drummied\"]\n[Result \"0-1\"]\n[UTCDate \"2014.06.30\"]\n[UTCTime \"22:00:11\"]\n[WhiteElo \"1525\"]\n[BlackElo \"1458\"]\n[WhiteRatingDiff \"-14\"]\n[BlackRatingDiff \"+14\"]\n[ECO \"A00\"]\n[Opening \"Mieses Opening\"]\n[TimeControl \"60+0\"]\n[Termination \"Time forfeit\"]")
    "retrieve white name" in {
      pgn.meta.white must beEqualTo("ShahinMohammad")
    }
    "retrieve black name" in {
      pgn.meta.black must beEqualTo("Drummied")
    }
    "retrieve opening name" in {
      pgn.meta.opening must beEqualTo("Mieses Opening")
    }
    "retrieve event type" in {
      pgn.meta.event must beEqualTo(EventTypes.RATED_BULLET)
    }
    "retrieve game URL" in {
      pgn.meta.url must beEqualTo("https://lichess.org/QSgawA0K")
    }
    "retrieve white elo" in {
      pgn.meta.whiteElo must beEqualTo(1525)
    }
    "retrieve black elo" in {
      pgn.meta.blackElo must beEqualTo(1458)
    }
    "ignore '?' in elo" in {
      val pgn = GameParser.parse("[Event \"Rated Bullet game\"]\n[Site \"https://lichess.org/QSgawA0K\"]\n[White \"ShahinMohammad\"]\n[Black \"Drummied\"]\n[Result \"0-1\"]\n[UTCDate \"2014.06.30\"]\n[UTCTime \"22:00:11\"]\n[WhiteElo \"1525?\"]\n[BlackElo \"1458?\"]\n[WhiteRatingDiff \"-14\"]\n[BlackRatingDiff \"+14\"]\n[ECO \"A00\"]\n[Opening \"Mieses Opening\"]\n[TimeControl \"60+0\"]\n[Termination \"Time forfeit\"]")
      pgn.meta.blackElo must beEqualTo(1458)
    }
    "use default elo when no solution" in {
      val pgn = GameParser.parse("[Event \"Rated Bullet game\"]\n[Site \"https://lichess.org/QSgawA0K\"]\n[White \"ShahinMohammad\"]\n[Black \"Drummied\"]\n[Result \"0-1\"]\n[UTCDate \"2014.06.30\"]\n[UTCTime \"22:00:11\"]\n[WhiteElo \"1525?\"]\n[BlackElo \"\"]\n[WhiteRatingDiff \"-14\"]\n[BlackRatingDiff \"+14\"]\n[ECO \"A00\"]\n[Opening \"Mieses Opening\"]\n[TimeControl \"60+0\"]\n[Termination \"Time forfeit\"]")
      pgn.meta.blackElo must beEqualTo(GameParser.DEFAULT_ELO)
      GameParser.getEloFromOptionString(None) must beEqualTo(GameParser.DEFAULT_ELO)
    }

    "retrieve evals when available" in {
      val pgn = GameParser.parse("[Event \"Rated Classical game\"]\n[White \"marwed\"]\n[Black \"Diamente\"]\n[WhiteElo \"1396\"]\n[BlackElo \"1701\"]\n[Opening \"Queen's Pawn Game #3\"]\n\n1. d4 { [%eval 0.22] } 1... d5 { [%eval 0.22] } 2. e3 { [%eval 0.15] } 2... Nf6 { [%eval 0.16] } 3. g3 { [%eval -0.22] } 3... Bg4 { [%eval 0.01] } 4. Nf3 { [%eval -0.42] } 4... a6 { [%eval #-4] } 0-1")
      pgn.evals must beEqualTo(List(
        Eval(0.22, None),
        Eval(0.22, None),
        Eval(0.15, None),
        Eval(0.16, None),
        Eval(-0.22, None),
        Eval(0.01, None),
        Eval(-0.42, None),
        Eval(0, Some(-4))
      ))
    }
  }
}
