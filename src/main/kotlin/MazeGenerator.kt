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
                val nearTopSideCells = maze.nearTopSideCells()
                for (i in 0..<nearTopSideCells.first().first) {
                    maze.setPass(i, nearTopSideCells.first().second)
                }
                for (i in nearTopSideCells.last().first + 1..<maze.mazeSize) {
                    maze.setPass(i, nearTopSideCells.last().second)
                }
            }

            true -> { //generate Start End in the Left and Right of Maze
                val nearLeftSideCells = maze.nearLeftSideCells()
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

}