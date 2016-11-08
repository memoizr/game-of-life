package gameoflife

case class Game(board: Board) {
  def nextGeneration: Game = {
    val neighbours = NeighbourFinder.findNeighbours(board.rows.toVector)
    val y: Vector[Vector[Cell]] = board.rows.zipWithIndex.map(rowWithIndex => rowWithIndex._1.zipWithIndex.map { cellWithIndex =>
      val neighbours1: Vector[Cell] = neighbours((cellWithIndex._2, rowWithIndex._2))
      val j = CellGod.callJudgementUpon(cellWithIndex._1)(neighbours1)
      j
    }).toVector

    Game(Board(y:_*))
  }
}
