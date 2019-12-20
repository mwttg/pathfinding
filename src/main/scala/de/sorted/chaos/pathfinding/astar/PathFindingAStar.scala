package de.sorted.chaos.pathfinding.astar

import de.sorted.chaos.pathfinding.level.{ Level, Position }

import scala.annotation.tailrec

object PathFindingAStar {
  def find(start: Position, finish: Position, level: Level): List[Position] = {
    val startNode   = Node(start, 0, 0, None)
    val initState   = AStarState.init(startNode, finish)
    val resultState = next(initState, finish, level)
    extractPath(resultState)
  }

  private def extractPath(state: AStarState) = {
    @tailrec
    def helper(node: Node, accumulator: List[Node]): List[Node] = {
      node.parent match {
        case None => accumulator
        case Some(n) =>helper(n, accumulator :+ node)
      }
    }

    val finish = state.path.reverse.head
    val result = helper(finish, List.empty[Node])
    result.reverse.map(node => node.position)
  }

  def next(state: AStarState, finish: Position, level: Level): AStarState = {
    @tailrec
    def helper(currentState: AStarState): AStarState =
      if (currentState.now.position == finish) {
        currentState
      } else {
        helper(currentState.next(level))
      }

    helper(state)
  }

}
