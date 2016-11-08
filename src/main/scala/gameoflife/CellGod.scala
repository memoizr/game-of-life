package gameoflife

object CellGod {
  val callJudgementUpon: Cell => Vector[Cell] => Cell = cell => neighbours => {
    neighbours.count(_ == Alive) match {
      case n if n == 3 => Alive
      case n if n == 3 | n == 2 => cell
      case n => Dead
    }
  }
}
