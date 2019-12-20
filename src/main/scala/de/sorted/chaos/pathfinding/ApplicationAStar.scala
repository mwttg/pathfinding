package de.sorted.chaos.pathfinding

import de.sorted.chaos.pathfinding.astar.PathFindingAStar
import de.sorted.chaos.pathfinding.common.PrettyPrint
import de.sorted.chaos.pathfinding.level.{Level, Position}

object ApplicationAStar {

  def main(args: Array[String]): Unit = {
    level(1, "level1.txt", Position(1,1), Position(15, 7))
    level(2, "level3.txt", Position(1,1), Position(79,79))
    level(3, "level3.txt", Position(1, 79), Position(79, 1))

  }

  private def level(number: Int, filename: String, start: Position, finish: Position): Unit = {
    println()
    println(s"Level - $number")
    val level = Level.from(filename)
    val result = PathFindingAStar.find(start, finish, level)
    PrettyPrint.draw(start, finish, result, level)

    println(s"Level - $number - REVERSE")
    val reverseResult = PathFindingAStar.find(finish, start, level)
    PrettyPrint.draw(finish, start, reverseResult, level)

  }
}
