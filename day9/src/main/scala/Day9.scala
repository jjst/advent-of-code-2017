import scala.io.Source


import scala.util.parsing.combinator._

case class ParseError(msg: String)

case class Group(children: Seq[Group])

case class Garbage(text: String)

object Group {
  val empty = Group(Seq.empty)
}

class StreamParsers extends RegexParsers {
  def children: Parser[Seq[Group]] = repsep(group | garbage, ",") ^^ { children =>
    children.collect { case g: Group => g }
  }
  def group: Parser[Group]   = "{" ~> children <~ "}"      ^^ { groups => Group(groups) }
  def garbage: Parser[Garbage] = "<" ~> ("" | """.*""".r) <~ ">" ^^ { g => Garbage(g) }
}

object StreamParser extends StreamParsers {
  def parse(str: String): Either[ParseError, Group] = parse(str, group)

  def parse[T](str: String, parser: Parser[T]): Either[ParseError, T] = {
    parse(parser, str) match {
      case NoSuccess(msg, text) => Left(ParseError(msg))
      case Success(group, next) => Right(group)
    }
  }
}

object Day9 extends App {
  val inputFilePath = args(0)

  val input = Source.fromFile(inputFilePath).getLines()
  println(input)
}
