package com.youyou

object AppMenu {

    fun printMenu(extended: Boolean): String {
        println("\n=== Menu ===")
        println("1. Generate a new maze")
        println("2. Load a maze")
        if (extended) {
            println("3. Save the maze")
            println("4. Display the maze")
            println("5. Find the escape")
        }
        println("0. Exit")
        return readln()
    }

    fun menu1(): Int {
        var mazeSize: Int
        do {
            println("Please, enter the size of new maze (5 or bigger)")
            mazeSize = readln().toInt()
        } while (mazeSize < 5)
        return mazeSize
    }

    fun menu2(): String {
        println("File name to load ? ")
        return readln()
    }

    fun menu3(): String {
        println("file name ? ")
        return readln()
    }

    fun fileErrorMessage(errorCode: Int, fileName: String = "") {
        if (errorCode == -1) {
            println("Cannot load the maze. It has an invalid format")
        }
        if (errorCode == 0){
            println("The file $fileName does not exist")
        }
    }

    fun menuOptionError() {
        println("Incorrect option. Please try again;")
    }

}