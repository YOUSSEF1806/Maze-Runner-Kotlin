package com.youyou

import kotlin.io.path.Path
import kotlin.io.path.exists

object MazeGame {
    var maze: Maze? = null
    var exitFlag = false

    fun launch() {
        while (!exitFlag) {
            processMenuChoice(AppMenu.printMenu(maze != null))
        }
        println("Bye!")
    }

    private fun processMenuChoice(choice: String) {
        when (choice) {
            "1" -> {
                actionMenu1(AppMenu.menu1())
                actionMenu4()
            }
            "2" -> {
                actionMenu2(AppMenu.menu2())
            }
            "3" -> {
                if (maze == null) {
                    AppMenu.menuOptionError()
                } else {
                    actionMenu3(AppMenu.menu3())
                }
            }
            "4" -> {
                if (maze == null) {
                    AppMenu.menuOptionError()
                } else {
                    actionMenu4()
                }
            }
            "0" -> { exitFlag = true }
            else -> { AppMenu.menuOptionError() }
        }
    }

    private fun actionMenu1(mazeSize: Int) {
        maze = MazeGenerator.generateFullMaze(mazeSize)
    }

    private fun actionMenu2(fileName: String): Boolean {
        if (Path(fileName).exists()) {
            val loadedMaze = MazeIO.loadFromFile(fileName)
            if (loadedMaze == null) {
                AppMenu.fileErrorMessage(-1)
                return false
            } else {
                maze = loadedMaze
                return true
            }
        } else {
            AppMenu.fileErrorMessage(0, fileName)
            return false
        }
    }

    private fun actionMenu3(fileName: String) {
        MazeIO.saveToFile(fileName, maze!!)
    }

    private fun actionMenu4() {
        println("\n$maze")
    }

}