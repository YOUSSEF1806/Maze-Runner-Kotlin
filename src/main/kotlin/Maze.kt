package com.youyou

import kotlin.random.Random

data class Maze(val nbLines: Int = 10, val nbCols: Int = 10) {
    private var maze = Array(nbLines) { Array(nbCols) { false } }

    fun generateFullMaze() {
        generateInnerMaze()
        finalizeMaze()
    }

    private fun finalizeMaze() {
        val sideStartEnd = Random.nextBoolean()
        when (sideStartEnd) {
            false -> { //generate Start End in the Top and Bottom of Maze
                val nearTopSideCells = nearTopSideCells()
                for (i in 0..<nearTopSideCells.first().first) {
                    maze[i][nearTopSideCells.first().second] = true
                }
                for (i in nearTopSideCells.last().first + 1..<nbLines) {
                    maze[i][nearTopSideCells.last().second] = true
                }
            }

            true -> { //generate Start End in the Left and Right of Maze
                val nearLeftSideCells = nearLeftSideCells()
                for (i in 0..<nearLeftSideCells.first().second) {
                    maze[nearLeftSideCells.first().first][i] = true
                }
                for (i in nearLeftSideCells.last().second + 1..<nbCols) {
                    maze[nearLeftSideCells.last().first][i] = true
                }
            }
        }
    }

    fun generateInnerMaze() {
        val currentCell = Pair(Random.nextInt(1, nbLines - 1), Random.nextInt(1, nbCols - 1))
        maze[currentCell.first][currentCell.second] = true
        val possibleCells = mutableMapOf(
            currentCell to
                    validCellsFromCellPlus2(currentCell.first, currentCell.second)
        )
        buildInnerMazeLoop(possibleCells)
    }

    private fun buildInnerMazeLoop(possibleCells: MutableMap<Pair<Int, Int>, List<Pair<Int, Int>>>) {
        var possibleCells1 = possibleCells
        if (possibleCells1.isEmpty())
            return

        val currentCell = possibleCells1.keys.random()
        val pickedCell = possibleCells1[currentCell]?.random()!!
        makePathFrom(currentCell, pickedCell)
        possibleCells1[pickedCell] = validCellsFromCellPlus2(pickedCell.first, pickedCell.second)

        possibleCells1 = possibleCells1.mapValues { it.value - pickedCell }
            .filterValues { it.isNotEmpty() }.toMutableMap()
        buildInnerMazeLoop(possibleCells1)
    }

    fun validCellsFromCellPlus2(x: Int, y: Int) =
        listOf(Pair(x - 2, y), Pair(x, y - 2), Pair(x + 2, y), Pair(x, y + 2))
            .filter {
                it.first in (1 until nbLines - 1)
                        && it.second in (1 until nbCols - 1)
                        && !maze[it.first][it.second]
            }

    private fun makePathFrom(currentCell: Pair<Int, Int>, pickedCell: Pair<Int, Int>) {
        val x1 = (currentCell.first + pickedCell.first) / 2
        val y1 = (currentCell.second + pickedCell.second) / 2
        maze[x1][y1] = true
        maze[pickedCell.first][pickedCell.second] = true
    }

    private fun nearLeftSideCells(): List<Pair<Int, Int>> {
        val selectedCols = mutableListOf<Int>()
        (0 until nbCols).indexOfFirst { getCol(it).contains(true) }.let { selectedCols.add(it) }
        (0 until nbCols).indexOfLast { getCol(it).contains(true) }.let { selectedCols.add(it) }

        val selectedColsCells = selectedCols.map { j ->
            maze.mapIndexedNotNull{ i, _->
                if (maze[i][j]) Pair(i, j) else null
            }.random()
        }
        return selectedColsCells
    }

    private fun nearTopSideCells(): List<Pair<Int, Int>> {
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

    private fun getCol(colIndex: Int): List<Boolean> = maze.map { it[colIndex] }

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
