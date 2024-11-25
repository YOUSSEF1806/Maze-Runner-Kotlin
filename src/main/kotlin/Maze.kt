package com.youyou

import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.io.path.writeLines
import kotlin.random.Random

data class Maze(var mazeSize: Int = 10) {
    private var maze = Array(mazeSize) { Array(mazeSize) {false} }

    constructor(mazeGrid: Array<Array<Boolean>>) : this(mazeGrid.size) {
        maze = mazeGrid
    }

    fun validCellsFromCellPlus2(x: Int, y: Int) =
        listOf(Pair(x - 2, y), Pair(x, y - 2), Pair(x + 2, y), Pair(x, y + 2))
            .filter {
                it.first in (1 until mazeSize - 1)
                        && it.second in (1 until mazeSize - 1)
                        && !maze[it.first][it.second]
            }

    fun makePathFrom(currentCell: Pair<Int, Int>, pickedCell: Pair<Int, Int>) {
        val x1 = (currentCell.first + pickedCell.first) / 2
        val y1 = (currentCell.second + pickedCell.second) / 2
        maze[x1][y1] = true
        maze[pickedCell.first][pickedCell.second] = true
    }

    fun nearLeftSideCells(): List<Pair<Int, Int>> {
        val selectedCols = mutableListOf<Int>()
        (0 until mazeSize).indexOfFirst { getCol(it).contains(true) }.let { selectedCols.add(it) }
        (0 until mazeSize).indexOfLast { getCol(it).contains(true) }.let { selectedCols.add(it) }

        val selectedColsCells = selectedCols.map { j ->
            maze.mapIndexedNotNull{ i, _->
                if (maze[i][j]) Pair(i, j) else null
            }.random()
        }
        return selectedColsCells
    }

    fun nearTopSideCells(): List<Pair<Int, Int>> {
        val selectedLines = mutableListOf<Int>()
        maze.indexOfFirst { it.contains(true) }.let { selectedLines.add(it) }
        maze.indexOfLast { it.contains(true) }.let { selectedLines.add(it) }

        val selectedLineCells = selectedLines.map { i ->
            maze[i].mapIndexedNotNull { j, value ->
                if (value) Pair(i, j) else null
            }.random()
        }
        return selectedLineCells
    }

    fun setPass(line: Int, col: Int) {
        maze[line][col] = true
    }

    // TODO Not sure this is right :p
    fun getCol(colIndex: Int): List<Boolean> = maze.map { it[colIndex] }

    fun toFileStrings(): List<String> = maze.map {
        it.joinToString { cell -> if (cell) "1" else "0" }
    }

    override fun toString(): String {
        return maze.joinToString("\n") {
            it.joinToString("") { block ->
                if (block) BLOCK.PASS.string else BLOCK.WALL.string
            }
        }
    }

}

enum class BLOCK(val string: String) {
    WALL("\u2588\u2588"),
    PASS("  ")
}
