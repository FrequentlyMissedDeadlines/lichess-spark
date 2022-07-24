package frequentlymisseddeadlines.lichess.parser

import org.specs2.mutable.Specification

class EventTypesSpec extends Specification {
  "EventTypes parser" should {
    "handle know types" in {
      EventTypes.fromString("Rated Blitz") must beEqualTo(EventTypes.RATED_BLITZ)
      EventTypes.fromString("Rated Bullet") must beEqualTo(EventTypes.RATED_BULLET)
      EventTypes.fromString("Rated Classical") must beEqualTo(EventTypes.RATED_CLASSICAL)
      EventTypes.fromString("Rated Correspondence") must beEqualTo(EventTypes.RATED_CORRESPONDENCE)
    }
    "handle unknown types" in {
      EventTypes.fromString("Chess 360") must beEqualTo(EventTypes.UNKNOWN)
      EventTypes.fromString("aezazfsdqf qsqfsd") must beEqualTo(EventTypes.UNKNOWN)
      EventTypes.fromString("") must beEqualTo(EventTypes.UNKNOWN)
    }
  }
}
