import com.youyou.Maze
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class TestMaze {

    @Test
    fun testGenerateFullMaze() {
        val maze = Maze(15, 35)
        maze.generateFullMaze()
        println("\n$maze")
    }

    @Test
    fun testGenerateInnerMaze() {
        val maze = Maze(11, 13)
        maze.generateInnerMaze()
        println("\n$maze")
    }

    @Test
    fun testPathFromCellPlus2() {
        val maze = Maze()
        var currentCell = Pair(Random.nextInt(1, maze.nbLines - 1), Random.nextInt(1, maze.nbCols - 1))
        var pathFromCellPlus2 = maze.validCellsFromCellPlus2(currentCell.first, currentCell.second)
        println("$currentCell ==> $pathFromCellPlus2")

        currentCell = Pair(2, 6)
        pathFromCellPlus2 = maze.validCellsFromCellPlus2(currentCell.first, currentCell.second)
        println("$currentCell ==> $pathFromCellPlus2")
    }

}