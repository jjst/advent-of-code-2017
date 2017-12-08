import scala.io.Source

case class Program(name: String, weight: Int, above: Seq[String] = Seq.empty)

object Day7 extends App {
  val inputFilePath = args(0)

  val ProgramWithNoDisk = """([a-z]+) \((\d+)\)""".r
  val ProgramWithDisk = """([a-z]+) \((\d+)\) -> (.*)""".r

  val programs = Source.fromFile(inputFilePath).getLines().map {
    case ProgramWithNoDisk(name, weight) => Program(name, weight.toInt)
    case ProgramWithDisk(name, weight, children) => Program(name, weight.toInt, children.split(", ").toVector)
  } toSeq
  val heldPrograms = programs.flatMap(_.above).toSet
  val allPrograms = programs.map(_.name).toSet
  val part1 = (allPrograms diff heldPrograms).head
  println(part1)
}
