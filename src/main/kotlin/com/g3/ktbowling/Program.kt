package com.g3.ktbowling

import java.util.*

fun main(args: Array<String>) {
    val rollsList = readInput(args)
    val rolls: Queue<Int> = ArrayDeque(rollsList)

    var totalScore = 0
    var currentFrameIndex = 0
    var hasATotal = true

    while (rolls.isNotEmpty() && currentFrameIndex++ < 10){
        var currentFrameSum = rolls.remove()

        if(currentFrameSum == 10){
            val (canContinue, newFrameSum) = assignBonus(rolls, currentFrameSum, 2)
            currentFrameSum = newFrameSum
            hasATotal = canContinue
        }
        else{
            if (canTake(rolls, 1)){
                hasATotal = false
                break
            }
            currentFrameSum += rolls.remove()

            if(currentFrameSum == 10){
                val (canContinue, newFrameSum) = assignBonus(rolls, currentFrameSum, 1)
                currentFrameSum = newFrameSum
                hasATotal = canContinue
            }
        }

        totalScore += currentFrameSum
    }

    printOutput(totalScore, hasATotal)
}

private fun assignBonus(rolls: Queue<Int>, frameValue: Int, bonusRolls: Int) : Pair<Boolean, Int>{
    if (canTake(rolls, bonusRolls)) {
        return Pair(false, -1)
    }

    return Pair(true, frameValue + computeBonus(rolls, bonusRolls))
}

private fun canTake(rolls: Queue<Int>, howMany: Int) = rolls.size < howMany

private fun computeBonus(rolls: Queue<Int>, howMany: Int) = rolls.take(howMany).sum()

private operator fun <E> Queue<E>.get(i: Int): E = elementAt(i)

private fun printOutput(totalScore: Int, hasATotal: Boolean) {
    println(if (hasATotal) totalScore else "")
}

private fun readInput(args: Array<String>): List<Int> {
    val rolls = args[0]
            .split(",")
            .map(String::toInt)
    return rolls
}