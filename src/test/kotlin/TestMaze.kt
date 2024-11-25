import com.youyou.Maze
import kotlin.random.Random
import kotlin.test.Test


class TestMaze {

    @Test
    fun testNewMazeAndPrint() {
        var maze = Maze(7)
        println("Maze size ${maze.mazeSize}\n$maze")
        maze = Maze(15)
        println("Maze size ${maze.mazeSize}\n$maze")
    }

    @Test
    fun testNewMazeWithMazeGrid() {
        val mazeGrid = Array(13) { Array(13) { false } }
        (0..<13).forEach { mazeGrid[it][it] = true }
        val maze = Maze(mazeGrid)
        println("Maze size ${maze.mazeSize}\n$maze")
    }

    @Test
    fun testSetPass() {
        val maze = Maze(8)
        (0..<maze.mazeSize).forEach { maze.setPass(it, maze.mazeSize/2) }
        println("Maze size ${maze.mazeSize}\n$maze")
    }

    @Test
    fun testGetCol() {
        val maze = Maze(8)
        (0..<maze.mazeSize).forEach { maze.setPass(it, it) }
        println("Maze size ${maze.mazeSize}\n$maze")
        val colIndex = 2
        val col = maze.getCol(colIndex)
        println("column $colIndex => $col")
    }

    @Test
    fun testPathFromCellPlus2() {
        val maze = Maze()
        var currentCell = Pair(Random.nextInt(1, maze.mazeSize - 1), Random.nextInt(1, maze.nbCols - 1))
        var pathFromCellPlus2 = maze.validCellsFromCellPlus2(currentCell.first, currentCell.second)
        println("$currentCell ==> $pathFromCellPlus2")

        currentCell = Pair(2, 6)
        pathFromCellPlus2 = maze.validCellsFromCellPlus2(currentCell.first, currentCell.second)
        println("$currentCell ==> $pathFromCellPlus2")
    }

}