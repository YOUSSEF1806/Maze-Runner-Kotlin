import com.youyou.MazeGenerator
import kotlin.test.Test

class TestMazeGenerator {

    @Test
    fun TestMultipleMazeGeneration() {
        var maze = MazeGenerator.generateFullMaze(9)
        println("Maze size ${maze.mazeSize}\n$maze")
        maze = MazeGenerator.generateFullMaze(22)
        println("Maze size ${maze.mazeSize}\n$maze")
        maze = MazeGenerator.generateFullMaze(35)
        println("Maze size ${maze.mazeSize}\n$maze")
        maze = MazeGenerator.generateFullMaze(5)
        println("Maze size ${maze.mazeSize}\n$maze")

    }
}