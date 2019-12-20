package de.sorted.chaos.pathfinding.common

import de.sorted.chaos.pathfinding.level.{Level, Position}

object PrettyPrint {
  import de.sorted.chaos.pathfinding.level.LevelObject._

  def draw(start: Position, finish: Position, path: List[Position], level: Level): Unit = {
    val dimension = level.dimension
    val levelMap = level.levelMap
    val resultPath = path.toSet

    for (y <- 0 until dimension.height) {
      for (x <- 0 until dimension.width) {
        if (levelMap.at(x, y) == Wall) {
          print(s"${0x2588.toChar}")
        } else if (Position(x, y) == start) {
          print("S")
        } else if (Position(x, y) == finish) {
          print("F")
        } else if(resultPath.contains(Position(x, y))) {
          print(s"${0xB7.toChar}")
        } else
          print(" ")
        }

      println()
    }
  }
}
