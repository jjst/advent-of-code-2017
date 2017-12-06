import scala.io.Source

case class MemoryState(memoryBanks: Vector[Int]) {
  def redistribute(): MemoryState = {
    val mostLoadedBankValue = memoryBanks.max
    val mostLoadedBankIndex = memoryBanks.indexOf(mostLoadedBankValue)
    val redistributedBanks =
      (1 to mostLoadedBankValue).foldLeft(memoryBanks.updated(mostLoadedBankIndex, 0)) { (banks, offset) =>
        val idx = (mostLoadedBankIndex + offset) % memoryBanks.size
        banks.updated(idx, banks(idx) + 1)
      }
    MemoryState(redistributedBanks)
  }
}

object Day6 extends App {
  val inputFilePath = args(0)
  val initialMemoryState = {
    val banks = Source.fromFile(inputFilePath).getLines().next().split('\t').map(_.toInt).toVector
    MemoryState(banks)
  }

  lazy val memoryStates: Stream[MemoryState] = initialMemoryState #:: memoryStates.map { _.redistribute() }

  val firstSeenBefore = memoryStates
    .scanLeft(Seq.empty[MemoryState]) { (states, state) => states :+ state }
    .zipWithIndex
    .collectFirst { case (memStates, idx) if memStates.distinct.size != memStates.size => idx }

  firstSeenBefore.foreach { idx => println(idx - 1) }
}
