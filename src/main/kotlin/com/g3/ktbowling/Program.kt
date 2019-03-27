package com.g3.ktbowling

import java.util.*

fun main(args: Array<String>) {
    val rollsList = readInput(args)
    var (hasATotal, totalScore) = playTheGame(rollsList)
    printOutput(totalScore, hasATotal)
}

private fun playTheGame(rollsList: List<Roll>): Pair<Boolean, Int> {
    val rolls = BowlingRolls(rollsList)
    var hasATotal = true
    var totalScore = 0
    var currentFrameIndex = 0

    while (rolls.hasRolls() && currentFrameIndex++ < 10) {
        val (_, nextRollValue) = rolls.takeNextRoll()
        var currentFrameSum = nextRollValue.rollValue

        if (currentFrameSum == 10) {
            val (canContinue, newFrameSum) = rolls.assignBonus(currentFrameSum, 2)
            currentFrameSum = newFrameSum
            hasATotal = canContinue
        } else {
            val (canContinue, nextRollValue) = rolls.takeNextRoll()
            currentFrameSum += nextRollValue.rollValue
            hasATotal = canContinue

            if (currentFrameSum == 10) {
                val (canContinue, newFrameSum) = rolls.assignBonus(currentFrameSum, 1)
                currentFrameSum = newFrameSum
                hasATotal = canContinue
            }
        }

        totalScore += currentFrameSum
    }
    return Pair(hasATotal, totalScore)
}

private fun takeNextRoll(rolls: Queue<Roll>) : Pair<Boolean, Roll>{
    if (notCanTake(rolls, 1)) {
        return Pair(false, Roll(0))
    }
    return Pair(true, rolls.remove())
}

private fun assignBonus(rolls: Queue<Roll>, frameValue: Int, bonusRolls: Int) : Pair<Boolean, Int>{
    if (notCanTake(rolls, bonusRolls)) {
        return Pair(false, -1)
    }

    return Pair(true, frameValue + computeBonus(rolls, bonusRolls))
}

private fun notCanTake(rolls: Queue<Roll>, howMany: Int) = rolls.size < howMany

private fun computeBonus(rolls: Queue<Roll>, howMany: Int) = rolls.take(howMany).sumBy { it.rollValue }

private operator fun <E> Queue<E>.get(i: Int): E = elementAt(i)

private fun printOutput(totalScore: Int, hasATotal: Boolean) {
    println(if (hasATotal) totalScore else "")
}

private fun readInput(args: Array<String>): List<Roll> {
    val rolls = args[0]
            .split(",")
            .map(String::toInt)
            .map{Roll(it)}

    return rolls
}

