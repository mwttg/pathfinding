package de.sorted.chaos.pathfinding.astar

import de.sorted.chaos.pathfinding.astar.AStarState.neighboursOf
import de.sorted.chaos.pathfinding.level.{ Level, Position }

final case class AStarState(now: Node, open: Set[Node], closed: Set[Node], path: List[Node], finish: Position) {
  def next(level: Level): AStarState = {
    val newNeighbours = neighboursOf(this.now, this.finish, level)
    val nextOpen      = this.open.union(newNeighbours).diff(this.closed)
    val nextNode      = nextOpen.min

    AStarState(
      now    = nextNode,
      open   = nextOpen - nextNode,
      closed = this.closed + nextNode,
      path   = this.path :+ this.now,
      finish = this.finish
    )
  }
}

object AStarState {
  import de.sorted.chaos.pathfinding.level.LevelObject._

  def init(startNode: Node, finish: Position): AStarState =
    AStarState(
      now    = startNode,
      open   = Set.empty[Node],
      closed = Set(startNode),
      path   = List.empty[Node],
      finish = finish
    )

  private def neighboursOf(node: Node, finish: Position, level: Level) = {
    // no diagonals possible
    val maybeNewPositions = Set(
      Position(node.position.x, node.position.y - 1),
      Position(node.position.x, node.position.y + 1),
      Position(node.position.x - 1, node.position.y),
      Position(node.position.x + 1, node.position.y)
    )
    val newNeighbourPositions = maybeNewPositions.filter(neighbourPosition => level.levelMap.at(neighbourPosition) != Wall)
    newNeighbourPositions.map(newPosition => node.createChild(newPosition, finish))
  }
}
