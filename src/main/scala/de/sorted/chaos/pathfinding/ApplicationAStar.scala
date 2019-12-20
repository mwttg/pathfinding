package de.sorted.chaos.pathfinding

import de.sorted.chaos.pathfinding.level.Level

object ApplicationAStar {

  def main(args: Array[String]): Unit = {
    val level = Level.from("level1.txt")
    level.prettyPrint()
  }
}
