package gameoflife

sealed trait Cell

case object Alive extends Cell {
  def kill = Dead
}

case object Dead extends Cell {
  def resuscitate = Alive
}
