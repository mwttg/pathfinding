package de.sorted.chaos.pathfinding.level

import de.sorted.chaos.pathfinding.common.FileUtilities

import LevelObject._

final case class Level(dimension: Dimension, levelMap: LevelMap) {
  def prettyPrint(): Unit = {
    for (y <- 0 until dimension.height) {
      for (x <- 0 until dimension.width) {
        if (levelMap.at(x, y) == Wall) {
          print(s"${0x2588.toChar}")
        } else {
          print(" ")
        }
      }

      println()
    }
  }
}

object Level {
  def empty: Level = Level(
    dimension = Dimension(
      width = 0,
      height = 0
    ),
    levelMap = LevelMap.empty)

  def from(filename: String): Level = {
    val lines = FileUtilities.read(filename)
    val dimension = Dimension.of(lines)
    val grid = LevelMap.from(lines)
    Level(dimension, grid)
  }
}
