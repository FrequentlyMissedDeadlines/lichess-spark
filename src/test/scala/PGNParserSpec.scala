import frequentlymisseddeadlines.lichess.parser.PGNParser
import org.specs2.mutable._

object PGNParserSpec extends Specification  {

  "PGN Parser " should {
    val pgn = PGNParser.parse("[Event \"Rated Bullet game\"]\n[Site \"https://lichess.org/QSgawA0K\"]\n[White \"ShahinMohammad\"]\n[Black \"Drummied\"]\n[Result \"0-1\"]\n[UTCDate \"2014.06.30\"]\n[UTCTime \"22:00:11\"]\n[WhiteElo \"1525\"]\n[BlackElo \"1458\"]\n[WhiteRatingDiff \"-14\"]\n[BlackRatingDiff \"+14\"]\n[ECO \"A00\"]\n[Opening \"Mieses Opening\"]\n[TimeControl \"60+0\"]\n[Termination \"Time forfeit\"]")
    "retrieve white name" in {
      pgn.meta.white must beEqualTo("ShahinMohammad")
    }
    "retrieve black name" in {
      pgn.meta.black must beEqualTo("Drummied")
    }
    "retrieve opening name" in {
      pgn.meta.opening must beEqualTo("Mieses Opening")
    }
    "retrieve white elo" in {
      pgn.meta.whiteElo must beEqualTo(1525)
    }
    "retrieve black elo" in {
      pgn.meta.blackElo must beEqualTo(1458)
    }
  }
}
