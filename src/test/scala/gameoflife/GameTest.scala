package gameoflife

class GameTest extends BaseTest {
  import Board._
  it should "Do underpopulation" in {
    val initial = Board(
      Row(Dead, Dead, Dead, Dead),
      Row(Dead, Alive, Dead, Dead),
      Row(Dead, Dead, Dead, Dead),
      Row(Dead, Dead, Dead, Dead)
    )

    val expectedEnd = Board(
      Row(Dead, Dead, Dead, Dead),
      Row(Dead, Dead, Dead, Dead),
      Row(Dead, Dead, Dead, Dead),
      Row(Dead, Dead, Dead, Dead)
    )
    val end = Game(initial).nextGeneration.board

    end shouldBe expectedEnd
  }

  it should "resuscitate" in {
    val initial = Board(
      Row(Dead, Dead, Alive, Dead),
      Row(Dead, Alive, Dead, Dead),
      Row(Dead, Dead, Alive, Dead),
      Row(Dead, Dead, Dead, Alive)
    )

    val expectedEnd = Board(
      Row(Dead, Dead, Alive, Dead),
      Row(Dead, Alive, Alive, Dead),
      Row(Dead, Dead, Alive, Dead),
      Row(Dead, Dead, Alive, Alive)
    )
    val end = Game(initial).nextGeneration.board

    end shouldBe expectedEnd
  }

  it should "kill" in {
    val initial = Board(
      Row(Dead, Dead, Alive, Dead),
      Row(Dead, Alive, Alive, Alive),
      Row(Dead, Dead, Alive, Dead),
      Row(Dead, Dead, Dead, Dead)
    )

    val expectedEnd = Board(
      Row(Dead, Alive, Alive, Alive),
      Row(Dead, Alive, Dead, Alive),
      Row(Dead, Alive, Alive, Alive),
      Row(Dead, Dead, Dead, Dead)
    )
    val end = Game(initial).nextGeneration.board

    end shouldBe expectedEnd
  }
}
