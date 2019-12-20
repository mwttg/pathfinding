package de.sorted.chaos.pathfinding.level

import de.sorted.chaos.pathfinding.level.LevelObject._

// Todo: Think about another level representation like:
// Obstacle(position: Position, type: Type)
//   Type = Wall, Rock, Door, Water, etc...
// --> obstacles: Set[Obstacle] instead of grid: Vector[Vector[LevelObject]]

final case class LevelMap(grid: Vector[Vector[LevelObject]]) {
  def at(position: Position): LevelObject = grid(position.y)(position.x)

  def at(x: Int, y: Int): LevelObject = grid(y)(x)
}

object LevelMap {
  def empty: LevelMap = LevelMap(Vector.empty[Vector[LevelObject]])

  def from(lines: Vector[String]): LevelMap = {
    lines.foldLeft(LevelMap.empty) { (rowAccumulator, line) => {
      val rowResult = line.foldLeft(Vector.empty[LevelObject]) { (columnAccumulator, character) => {
        character match {
          case '#' => columnAccumulator :+ Wall
          case '.' => columnAccumulator :+ Space
          case '@' => columnAccumulator :+ Player
        }
      }
      }

      rowAccumulator.copy(
        grid = rowAccumulator.grid :+ rowResult
      )
    }
    }
  }
}
