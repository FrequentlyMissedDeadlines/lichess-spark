package frequentlymisseddeadlines.lichess.parser

import org.specs2.mutable.Specification

class EvalSpec extends Specification {
  "Eval" should {
    "be exportable to CSV" in {
      Eval(1.0, None).toCSV() must beEqualTo("1.0")
      Eval(0.0, Some(-2)).toCSV() must beEqualTo("#-2")
      Eval(0.0, Some(2)).toCSV() must beEqualTo("#2")
    }
  }
}
