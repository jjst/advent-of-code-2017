import org.scalatest.{FunSuite, Matchers}

import scala.util.parsing.combinator._

class StreamParserSpec extends FunSuite with Matchers {
  test("Empty group") {
    StreamParser.parse("{}") shouldBe Right(Group.empty)
  }

  test("Nested group") {
    StreamParser.parse("{{}}") shouldBe Right(Group(Seq(Group.empty)))
  }

  test("Nested groups") {
    StreamParser.parse("{{},{}}") shouldBe Right(Group(Seq(Group.empty, Group.empty)))
  }

  test("Garbage") {
    StreamParser.parse("<>", StreamParser.garbage) shouldBe Right(Garbage(""))
  }

  test("More garbage") {
    StreamParser.parse("<{}>", StreamParser.garbage) shouldBe Right(Garbage("{}"))
  }

  test("More garbage 2") {
    StreamParser.parse("<<<fdsgh>", StreamParser.garbage) shouldBe Right(Garbage("<<fdsgh"))
  }

  test("Group with garbage") {
    StreamParser.parse("{<>}") shouldBe Right(Group.empty)
  }
}
