package de.sorted.chaos.pathfinding

import de.sorted.chaos.pathfinding.astar.PathFindingAStar
import de.sorted.chaos.pathfinding.common.PrettyPrint
import de.sorted.chaos.pathfinding.level.{Level, Position}

object ApplicationAStar {

  def main(args: Array[String]): Unit = {
    level(1, Position(1,1), Position(15, 7))
    level(3, Position(1,1), Position(79,79))
  }

  private def level(number: Int, start: Position, finish: Position): Unit = {
    println()
    println(s"Level - $number")
    val level = Level.from(s"level$number.txt")
    val result = PathFindingAStar.find(start, finish, level)
    PrettyPrint.draw(start, finish, result, level)

    println(s"Level - $number - REVERSE")
    val reverseResult = PathFindingAStar.find(finish, start, level)
    PrettyPrint.draw(finish, start, reverseResult, level)

  }
}
