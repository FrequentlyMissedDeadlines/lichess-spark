package frequentlymisseddeadlines.lichess.parser

case class Eval(eval: Double, mateIn: Option[Int]) {
  def toCSV(): String = {
    mateIn.map("#" + _).getOrElse(eval+"")
  }
}

object Eval {
  def fromString(str: String): Eval = str match {
    case str if str.startsWith("#") => Eval(0, Some(str.substring(1, str.length).toInt))
    case _ => Eval(str.toDouble, None)
  }
}
