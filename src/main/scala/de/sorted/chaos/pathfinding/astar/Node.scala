package de.sorted.chaos.pathfinding.astar

import de.sorted.chaos.pathfinding.level.Position

/**
  *
  * @param position the position of the Node (Position(x: Int, y: Int))
  * @param gCost    the distance between the current node and the start node
  * @param hCost    (heuristic) the distance between the current node and the end node
  * @param parent   the parent Node
  */
final class Node(val position: Position, val gCost: Int, val hCost: Int, val parent: Option[Node]) extends Ordered[Node] {
  def cost: Int = gCost + hCost

  def createChild(newPosition: Position, endPosition: Position): Node = {
    val hCost = calculateHCost(newPosition, endPosition)
    val gCost = calculateGCost(newPosition, endPosition)
    Node(newPosition, gCost, hCost, Some(this))
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

  // We omit the parent from equals and hashCode otherwise, the AStarState.next method will add every neighbour
  // every time, because the parent changes every time ;)
  override def equals(other: Any): Boolean = other match {
    case that: Node =>
      position == that.position &&
        gCost == that.gCost &&
        hCost == that.hCost
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(position, gCost, hCost)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}

object Node {
  def apply(position: Position, gCost: Int, hCost: Int, parent: Option[Node]): Node = new Node(position, gCost, hCost, parent)
}
