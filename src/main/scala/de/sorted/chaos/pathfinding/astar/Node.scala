package de.sorted.chaos.pathfinding.astar

import de.sorted.chaos.pathfinding.level.Position

/**
  *
  * @param position the position of the Node (Position(x: Int, y: Int))
  * @param gCost    the distance between the current node and the start node
  * @param hCost    (heuristic) the distance between the current node and the end node
  */
final case class Node(position: Position, gCost: Int, hCost: Int) extends Ordered[Node] {
  def cost: Int = gCost + hCost

  def createChild(newPosition: Position, endPosition: Position): Node = {
    val hCost = calculateHCost(newPosition, endPosition)
    val gCost = calculateGCost(newPosition, endPosition)
    Node(newPosition, gCost, hCost)
  }

  // simple pythagoras (times 10 because we don't like double numbers ;/ )
  private def calculateHCost(from: Position, to: Position) = {
    val x = Math.abs(from.x - to.x)
    val y = Math.abs(from.y - to.y)
    (Math.sqrt((x * x) + (y * y)) * 10).toInt
  }

  // Manhattan distance
  private def calculateGCost(from: Position, to: Position) = {
    val x = Math.abs(from.x - to.x)
    val y = Math.abs(from.y - to.y)
    x + y
  }

  override def compare(that: Node): Int = this.cost.compare(that.cost)
}
