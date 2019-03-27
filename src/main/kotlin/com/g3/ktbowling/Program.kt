package com.g3.ktbowling

import java.util.*

fun main(args: Array<String>) {
    val rollsList = readInput(args)
    var totalScore = BowlingGame.playTheGame(rollsList)
    printOutput(totalScore)
}

private operator fun <E> Queue<E>.get(i: Int): E = elementAt(i)

private fun printOutput(totalScore: FrameScore) {
    println(totalScore)
}

private fun readInput(args: Array<String>): List<Roll> {
    val rolls = args[0]
            .split(",")
            .map(String::toInt)
            .map{Roll(it)}

    return rolls
}

