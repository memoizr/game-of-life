package gameoflife

class BoardTest extends BaseTest {

  import Board._

  "A board" should "give a list of neighbours" in {
    val board = Board(
      Row(Dead, Dead, Dead),
      Row(Dead, Dead, Dead),
      Row(Dead, Dead, Dead)
    )

    val neighbours = board.neighbours

    neighbours.size shouldBe 9
    neighbours.map(_._2.size).toSet.size shouldBe 1
    neighbours.map(_._2.size).toSet.head shouldBe 8
  }

  "neighbour finder" should "give a list of neighbours" in {
    val board = Vector(
      Vector(1, 2, 3),
      Vector(4, 5, 6),
      Vector(7, 8, 9),
      Vector(10, 11, 12)
    )

    val neighbours = NeighbourFinder.findNeighbours(board)

    neighbours.size shouldBe 12
    neighbours.map(_._2.size).toSet.size shouldBe 1
    neighbours.map(_._2.size).toSet.head shouldBe 8
    neighbours((0,0)).sorted shouldBe List(2, 3, 4, 5, 6, 10, 11, 12)
    neighbours((1,1)).sorted shouldBe (1 to 9).filterNot(_ == 5)
    neighbours((2,3)).sorted shouldBe List(1, 2, 3, 7, 8, 9, 10, 11)
  }
}

object NeighbourFinder {
  def findNeighbours[A](vec: Vector[Vector[A]]): (Int, Int) Map Vector[A] = {
    val height = vec.size
    val width = vec.head.size

    val tuples = for {
      (x, y) <- generateCoordinates(width)(height).toVector
      neighbours = for {
        xRange <- x - 1 to x + 1
        yRange <- y - 1 to y + 1
        if !(xRange == x && yRange == y)
      } yield vec((yRange + height) % height)((xRange + width) % width)
    } yield (x,y) -> neighbours.toVector
    tuples.toMap
  }

  private val generateCoordinates: Int => Int => Seq[(Int, Int)] = { width => height =>
    for {
      x <- 0 until width
      y <- 0 until height
    } yield (x, y)
  }
}

import Board._

case class Board(rows: Row*) {
  def neighbours: (Int, Int) Map Vector[Cell] = NeighbourFinder.findNeighbours(rows.toVector)
}

object Board {
  type Row = Vector[Cell]
  val Row = Vector
}
