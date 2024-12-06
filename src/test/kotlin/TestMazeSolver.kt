import com.youyou.MazeGenerator
import com.youyou.MazeSolver
import kotlin.random.Random
import kotlin.test.Test

class TestMazeSolver {
    @Test
    fun testSolveRandomMazes() {
        val randomSizes = List(5) { Random.nextInt(5, 45) }
        randomSizes.forEach {
            val maze = MazeGenerator.generateFullMaze(it)
            println("\n****************")
            println("Maze Size ${maze.mazeSize}:")
            println("\n$maze")
            println("\n#################")
            val escapeTrail = MazeSolver.findEscape(maze)
            println("Solution is : $escapeTrail")
            println("\nSolved Maze: \n${maze.printSolution(escapeTrail)}")
        }
    }
}