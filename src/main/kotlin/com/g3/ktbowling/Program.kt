package com.g3.ktbowling

import java.util.*

fun main(args: Array<String>) {
    val rollsList = readInput(args)
    var totalScore = playTheGame(rollsList)
    printOutput(totalScore)
}

private fun playTheGame(rollsList: List<Roll>): FrameScore {
    val rolls = BowlingRolls(rollsList)
    var totalScore: FrameScore = FrameScoreWithValue()
    var currentFrameIndex = 0

    while (rolls.hasFrames() && totalScore.canContinue && currentFrameIndex++ < 10) {
        val frame = rolls.takeNextFrame()
        totalScore += frame.computeFrameScore()
    }
    return totalScore
}

private fun computeFrameScore(rolls: BowlingRolls): FrameScore {
    val firstRoll: Roll = rolls.takeNextRoll()
    var frameScore: FrameScore = FrameScoreWithValue(firstRoll.rollValue)

    if (firstRoll.isAStrike) {
        frameScore = rolls.assignBonus(10, 2)
    } else {
        frameScore += rolls.takeNextRoll()

        if (frameScore.IsASpare) {
            frameScore = rolls.assignBonus(10, 1)
        }
    }
    return frameScore
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

