package com.youyou

import kotlin.random.Random

object MazeGenerator {
    fun generateFullMaze(mazeSize: Int): Maze {
        val maze = generateInnerMaze(mazeSize)
        return finalizeMaze(maze)
    }

    private fun finalizeMaze(maze: Maze): Maze {
        val sideStartEnd = Random.nextBoolean()
        when (sideStartEnd) {
            false -> { //generate Start End in the Top and Bottom of Maze
                val nearTopSideCells = nearTopSideCells(maze)
                for (i in 0..<nearTopSideCells.first().first) {
                    maze.setPass(i, nearTopSideCells.first().second)
                }
                for (i in nearTopSideCells.last().first + 1..<maze.mazeSize) {
                    maze.setPass(i, nearTopSideCells.last().second)
                }
            }

            true -> { //generate Start End in the Left and Right of Maze
                val nearLeftSideCells = nearLeftSideCells(maze)
                for (i in 0..<nearLeftSideCells.first().second) {
                    maze.setPass(nearLeftSideCells.first().first, i)
                }
                for (i in nearLeftSideCells.last().second + 1..<maze.mazeSize) {
                    maze.setPass(nearLeftSideCells.last().first, i)
                }
            }
        }
        return maze
    }

    private fun generateInnerMaze(mazeSize: Int): Maze {
        val maze = Maze(mazeSize)
        val currentCell = Pair(
            Random.nextInt(1, maze.mazeSize - 1),
            Random.nextInt(1, maze.mazeSize - 1)
        )
        maze.setPass(currentCell.first, currentCell.second)
        val possibleCells = mutableMapOf(
            currentCell to
                    maze.validCellsFromCellPlus2(currentCell.first, currentCell.second)
        )
        buildInnerMazeLoop(maze, possibleCells)
        return maze
    }

    private fun buildInnerMazeLoop(maze: Maze, possibleCells: MutableMap<Pair<Int, Int>, List<Pair<Int, Int>>>) {
        var possibleCells1 = possibleCells
        if (possibleCells1.isEmpty())
            return

        val currentCell = possibleCells1.keys.random()
        val pickedCell = possibleCells1[currentCell]?.random()!!
        maze.makePathFrom(currentCell, pickedCell)
        possibleCells1[pickedCell] = maze.validCellsFromCellPlus2(pickedCell.first, pickedCell.second)

        possibleCells1 = possibleCells1.mapValues { it.value - pickedCell }
            .filterValues { it.isNotEmpty() }.toMutableMap()
        buildInnerMazeLoop(maze, possibleCells1)
    }

    private fun nearLeftSideCells(maze: Maze): List<Pair<Int, Int>> {
        val selectedCols = mutableListOf<Int>()
        (0 until maze.mazeSize).indexOfFirst { maze.getCol(it).contains(true) }.let { selectedCols.add(it) }
        (0 until maze.mazeSize).indexOfLast { maze.getCol(it).contains(true) }.let { selectedCols.add(it) }

        val selectedColsCells = selectedCols.map { j ->
            maze.getCol(j).mapIndexedNotNull{ i, _->
                if (maze.getLine(i)[j]) Pair(i, j) else null
            }.random()
        }
        return selectedColsCells
    }

    private fun nearTopSideCells(maze: Maze): List<Pair<Int, Int>> {
        val selectedLines = mutableListOf<Int>()
        (0 until maze.mazeSize).indexOfFirst { maze.getLine(it).contains(true) }.let { selectedLines.add(it) }
        (0 until maze.mazeSize).indexOfLast { maze.getLine(it).contains(true) }.let { selectedLines.add(it) }

        val selectedLineCells = selectedLines.map { i ->
            maze.getLine(i).mapIndexedNotNull { j, value ->
                if (value) Pair(i, j) else null
            }.random()
        }
        return selectedLineCells
    }


}