package com.youyou

import kotlin.io.path.Path
import kotlin.io.path.exists

object AppMenu {

    fun printMenu(extended: Boolean): String {
        println("=== Menu ===")
        println("1. Generate a new maze")
        println("2. Load a maze")
        if (extended) {
            println("3. Save the maze")
            println("4. Display the maze")
        }
        println("0. Exit")
        return readln()
    }

    fun processMenuChoice(choice: String, extended: Boolean) {
        when (choice) {
            "1" -> {  }
            "2" -> {  }
            "3" -> {  }
            "4" -> {  }
            "0" -> {  }
            else -> { }
        }
    }

    fun menu1(): Int {
        println("Please, enter the size of new maze")
        return readln().toInt()
    }

    fun menu2(): String {
        println("File name to load ? ")
        return readln()
    }

    fun actionMenu3() {
        println("file name ? ")
        val fileName = readln()
        //todo
    }

    fun fileErrorMessage(errorCode: Int, fileName: String = "") {
        if (errorCode == -1) {
            println("Cannot load the maze. It has an invalid format")
        }
        if (errorCode == 0){
            println("The file $fileName does not exist")
        }
    }
    fun actionMenu4() {
        println("\n${MazeGame}")
    }
}