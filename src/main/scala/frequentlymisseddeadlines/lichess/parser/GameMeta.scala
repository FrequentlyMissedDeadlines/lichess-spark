package frequentlymisseddeadlines.lichess.parser

case class GameMeta(white: String, black: String, whiteElo: Int, blackElo: Int, opening: String, event: EventTypes.EventType, url: String) {
  def toCSV(): String = {
    List(event, white, black, whiteElo, blackElo, opening, url).mkString(",")
  }
}
