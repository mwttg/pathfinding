package de.sorted.chaos.pathfinding.level

final case class Dimension(width: Int, height: Int)

object Dimension {
  def of(lines: Vector[String]): Dimension = {
    val height = lines.size
    val width = lines.head.toList.size
    Dimension(width, height)
  }
}
