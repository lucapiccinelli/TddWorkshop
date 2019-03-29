package com.g3.ktbowling

import java.util.*

fun main(args: Array<String>) {
    val rollsList = readInput(args)
    var totalScore = BowlingGame.playTheGame(rollsList)
    printOutput(totalScore)
}

private fun printOutput(totalScore: FrameScore) = println(totalScore)
private fun readInput(args: Array<String>): List<Roll> = RollImpl.fromCommaSeparatedString(args[0])

