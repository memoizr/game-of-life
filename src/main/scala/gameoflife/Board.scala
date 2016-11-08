package gameoflife

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
