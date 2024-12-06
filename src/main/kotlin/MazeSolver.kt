package com.youyou

object MazeSolver {
    fun findEscape(maze: Maze): List<Pair<Int, Int>> {
        val startEndCoord = getStartEnd(maze)
        var solutionTrail = listOf(listOf(startEndCoord.first()))
        var finalSolution: List<Pair<Int, Int>>
        do {
            solutionTrail = generateSolution(maze, solutionTrail)
            finalSolution = checkHasSolution(solutionTrail, startEndCoord.last())
        } while (finalSolution.isEmpty())

        return finalSolution
    }

    private fun getStartEnd(maze: Maze): List<Pair<Int, Int>> {
        val topLine = maze.getLine(0).indexOfFirst { it }.let { if (it != -1) Pair(0, it) else null }
        val bottomLine = maze.getLine(maze.mazeSize - 1)
            .indexOfFirst { it }
            .let { if (it != -1) Pair(maze.mazeSize - 1, it) else null }
        val leftCol = maze.getCol(0).indexOfFirst { it }.let { if (it != -1) Pair(it, 0) else null }
        val rightCol = maze.getCol(maze.mazeSize - 1)
            .indexOfFirst { it }
            .let { if (it != -1) Pair(it, maze.mazeSize - 1) else null }

        return listOfNotNull(topLine, bottomLine, leftCol, rightCol)
    }

    private fun generateSolution(maze: Maze, validSolutions: List<List<Pair<Int, Int>>>): List<List<Pair<Int, Int>>> {
        val tempList = validSolutions.toMutableList()
        validSolutions.forEach { initialTrail ->
            trailNextMove(maze, initialTrail)?.forEach { tempList.add(it) }
            tempList.remove(initialTrail)
        }
        return tempList.toList()
    }

    private fun trailNextMove(maze: Maze, solutionTrail: List<Pair<Int, Int>>): List<List<Pair<Int, Int>>>? {
        val validMoves = maze.validMoves(solutionTrail.last().first, solutionTrail.last().second)
        if (validMoves.isEmpty())
            return null
        return validMoves.filter { it !in solutionTrail }.map { solutionTrail + it }
    }

    private fun checkHasSolution(solutionTrail: List<List<Pair<Int, Int>>>, end: Pair<Int, Int>): List<Pair<Int, Int>> {
        return solutionTrail.find { it.last() == end } ?: emptyList()
    }
}