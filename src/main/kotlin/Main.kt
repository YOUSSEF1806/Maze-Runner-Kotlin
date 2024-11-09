package com.youyou

import kotlin.random.Random

fun main() {
    println("Please, enter the size of a maze")
    val (nbLines, nbCols) = readln().split(" ").map { it.toInt() }
    val maze = Maze(nbLines, nbCols)
    maze.generateFullMaze()
    println("\n$maze")
}


