package gameoflife

class CellTest extends BaseTest {
  "A live cell" should "be killed" in {
    val liveCell = Alive

    liveCell.kill shouldBe Dead
  }

  "A dead cell" should "be resuscitated" in {
    val deadCell = Dead

    deadCell.resuscitate shouldBe Alive
  }
}

sealed trait Cell

case object Alive extends Cell {
  def kill = Dead
}

case object Dead extends Cell {
  def resuscitate = Alive
}
