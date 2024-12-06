package com.youyou

data class Maze(val mazeSize: Int = 10) {
    private var maze = Array(mazeSize) { Array(mazeSize) {false} }

    constructor(mazeGrid: Array<Array<Boolean>>) : this(mazeGrid.size) {
        maze = mazeGrid
    }

    fun validMoves(x: Int, y: Int) =
        listOf(Pair(x - 1, y), Pair(x, y - 1), Pair(x + 1, y), Pair(x, y + 1))
            .filter {
                it.first in (1 until mazeSize)
                        && it.second in (1 until mazeSize)
                        && maze[it.first][it.second]
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

    fun setPass(line: Int, col: Int) {
        maze[line][col] = true
    }

    fun getLine(lineIndex: Int) = maze[lineIndex].toList()
    fun getCol(colIndex: Int): List<Boolean> = maze.map { it[colIndex] }

    fun toFileStrings(): List<String> = maze.map {
        it.joinToString(" ") { cell -> if (cell) "1" else "0" }
    }

    fun printSolution(solutionTrail: List<Pair<Int, Int>>): String {
        return maze.mapIndexed { i, line ->
            line.mapIndexed { j, cell ->
                if (!cell)
                    BLOCK.WALL.string
                else
                    if (Pair(i,j) in solutionTrail)
                        BLOCK.TRAIL.string
                    else BLOCK.PASS.string
            }
        }.joinToString("\n") { it.joinToString("") }
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
    PASS("  "),
    TRAIL("//")
}
