package frequentlymisseddeadlines.lichess.parser

import frequentlymisseddeadlines.lichess.parser.EventTypes.UNKNOWN
import org.specs2.mutable.Specification

class GameMetaSpec extends Specification {
  "GameMeta" should {
    "be exportable to CSV" in {
      GameMeta("white", "black", 1000, 1000, "opening", UNKNOWN, "url").toCSV() must beEqualTo("UNKNOWN,white,black,1000,1000,opening,url")
    }
  }
}
