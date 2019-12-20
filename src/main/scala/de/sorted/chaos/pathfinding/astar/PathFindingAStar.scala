package de.sorted.chaos.pathfinding.astar

import de.sorted.chaos.pathfinding.level.{ Level, Position }

import scala.annotation.tailrec

object PathFindingAStar {
  def find(start: Position, finish: Position, level: Level): List[Position] = {
    val startNode   = Node(start, 0, 0)
    val initState   = AStarState.init(startNode, finish)
    val resultState = next(initState, finish, level)
    resultState.path.map(node => node.position)

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
