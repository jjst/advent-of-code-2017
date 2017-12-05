import scala.annotation.tailrec
import scala.io.Source

object Day5 extends App {
  val inputFilePath = args(0)
  val initialInstructions: Seq[Int] = Source.fromFile(inputFilePath).getLines().map(_.toInt).toVector

  def countJumps(initialInstructions: Seq[Int], offsetUpdater: (Int => Int)) = {
    @tailrec
    def jump(instructions: Seq[Int], currentIndex: Int = 0, jumpCount: Int = 0): Int = {
      instructions.lift(currentIndex) match {
        case Some(offset) => jump(instructions.updated(currentIndex, offsetUpdater(offset)), currentIndex + offset, jumpCount + 1)
        case None => jumpCount
      }
    }
    jump(initialInstructions)
  }

  println(countJumps(initialInstructions, offsetUpdater = (_ + 1)))
  println(countJumps(initialInstructions, offsetUpdater = (offset => if (offset >= 3) offset - 1 else offset + 1)))
}
