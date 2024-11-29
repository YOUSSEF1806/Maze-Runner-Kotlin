package com.youyou

import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.io.path.writeLines

object MazeIO {

    fun saveToFile(fileName: String, maze: Maze) {
        Path(fileName).writeLines(maze.toFileStrings())
    }

    fun loadFromFile(fileName: String): Maze? {
        val tempMaze = Path(fileName).readLines()
            .map {
                it.split(" ").map { chr -> chr == "1" }.toTypedArray()
            }.toTypedArray()
        val count = tempMaze.map { it.size == tempMaze.size }.count { true }
        if (count != tempMaze.size)
            return null

        return Maze(tempMaze)
    }
}