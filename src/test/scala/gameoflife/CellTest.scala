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

