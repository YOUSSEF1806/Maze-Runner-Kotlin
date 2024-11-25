package com.youyou

import kotlin.io.path.Path
import kotlin.io.path.exists

object MazeGame {
    var maze: Maze? = null


    fun processMenuChoice(choice: String, extended: Boolean) {
        when (choice) {
            "1" -> {  }
            "2" -> {  }
            "3" -> {  }
            "4" -> {  }
            "0" -> {  }
            else ->
        }
    }

    fun actionMenu1() {
        println("Please, enter the size of new maze")
        val mazeSize = readln().toInt()
        val maze = MazeGenerator.generateFullMaze(mazeSize)
        println("\n$maze")
    }

    fun actionMenu2() {
        println("File name to load ? ")
        val fileName = readln()
        if (Path(fileName).exists()) {
            val loadedMaze = Maze.loadFromFile(fileName)
            if (loadedMaze == null) {
                AppMenu.fileErrorMessage(-1)
            }
        } else {
            AppMenu.fileErrorMessage(0, fileName)
        }
    }

    fun actionMenu3() {
        println("file name ? ")
        val fileName = readln()
        //todo
    }

    fun actionMenu4() {
        println("\n${MazeGame}")
    }

}