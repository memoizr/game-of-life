package gameoflife

class CellGodTest extends BaseTest {

  "Cell updater" should "kill a cell if surrounded by fewer than two" in {
    val killedCell1 = CellGod.callJudgementUpon(Alive)(Vector(Dead, Alive, Dead, Dead))
    val killedCell2 = CellGod.callJudgementUpon(Alive)(Vector(Dead, Dead, Dead, Dead))

    killedCell1 shouldBe Dead
    killedCell2 shouldBe Dead
  }

  "A live cell" should "survive if surrounded by between 2 and 3 live neighbours" in {
    val aliveCell1 = CellGod.callJudgementUpon(Alive)(Vector(Alive, Dead, Alive, Dead))
    val aliveCell2 = CellGod.callJudgementUpon(Alive)(Vector(Alive, Alive, Alive, Dead))
    aliveCell1 shouldBe Alive
    aliveCell2 shouldBe Alive
  }

  "A live cell" should "die if surrounded by more than 3 live neighbours" in {
    val deadCell = CellGod.callJudgementUpon(Alive)(Vector(Alive, Alive, Alive, Alive))
    deadCell shouldBe Dead
  }

  "A dead cell" should "should resuscitate if surrounded by exactly 3 live neighbours" in {
    val deadCell = CellGod.callJudgementUpon(Dead)(Vector(Alive, Alive, Alive, Dead))
    deadCell shouldBe Alive
  }

  "A dead cell" should "stay dead if surrounded by any other than 3 live neighbours" in {
    val deadCell0 = CellGod.callJudgementUpon(Dead)(Vector(Dead, Dead, Dead, Dead))
    val deadCell1 = CellGod.callJudgementUpon(Dead)(Vector(Dead, Dead, Alive, Dead))
    val deadCell2 = CellGod.callJudgementUpon(Dead)(Vector(Dead, Alive, Alive, Dead))
    deadCell0 shouldBe Dead
    deadCell1 shouldBe Dead
    deadCell2 shouldBe Dead
  }
}

