package frequentlymisseddeadlines.lichess.parser

object EventTypes extends Enumeration {
  type EventType = Value
  val RATED_BLITZ, RATED_BULLET, RATED_CLASSICAL, RATED_CORRESPONDENCE, UNKNOWN = Value

  def fromString(str: String): EventType = str match {
    case s if s.startsWith("Rated Blitz") => RATED_BLITZ
    case s if s.startsWith("Rated Bullet") => RATED_BULLET
    case s if s.startsWith("Rated Classical") => RATED_CLASSICAL
    case s if s.startsWith("Rated Correspondence") => RATED_CORRESPONDENCE
    case _ => UNKNOWN
  }
}
