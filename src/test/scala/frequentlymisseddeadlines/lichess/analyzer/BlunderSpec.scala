package frequentlymisseddeadlines.lichess.analyzer

import frequentlymisseddeadlines.lichess.parser.EventTypes.UNKNOWN
import frequentlymisseddeadlines.lichess.parser.GameMeta
import org.specs2.mutable.Specification

class BlunderSpec extends Specification {
  val meta = GameMeta("white", "black", 1000, 1000, "opening", UNKNOWN, "url")
  "Blunder" should {
    "be exportable to CSV" in {
      Blunder(meta, "10", BlunderType.MATE_LOSS).toCSV() must beEqualTo("UNKNOWN,white,black,1000,1000,opening,url,10,MATE_LOSS")
    }
  }
}
