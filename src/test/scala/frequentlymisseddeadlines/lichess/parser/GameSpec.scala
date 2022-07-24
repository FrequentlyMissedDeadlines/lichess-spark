package frequentlymisseddeadlines.lichess.parser

import frequentlymisseddeadlines.lichess.parser.EventTypes.UNKNOWN
import org.specs2.mutable.Specification

class GameSpec extends Specification {
  val meta = GameMeta("white", "black", 1000, 1000, "opening", UNKNOWN, "url")
  "Game" should {
    "be exportable to CSV" in {
      Game(meta, Moves(), List(Eval(1.0, None))).toCSV() must beEqualTo("UNKNOWN,white,black,1000,1000,opening,url,Moves(),Eval(1.0,None)")
    }
  }
}
