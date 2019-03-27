package com.g3.ktbowling

import java.util.*

fun main(args: Array<String>) {
    val rollsList = readInput(args)
    val rolls: Queue<Int> = ArrayDeque(rollsList)

    var totalScore = 0
    var currentFrameIndex = 0
    while (rolls.isNotEmpty() && currentFrameIndex++ < 10){
        var currentFrameSum = rolls.remove()

        if(currentFrameSum == 10){
            if(rolls.size < 2){
                totalScore = -1
                break
            }

            currentFrameSum += computeBonus(rolls, 2)
        }
        else{
            if (rolls.size < 1){
                totalScore = -1
                break
            }
            currentFrameSum += rolls.remove()

            if(currentFrameSum == 10){
                if (rolls.size < 1){
                    totalScore = -1
                    break
                }
                currentFrameSum += computeBonus(rolls, 1)
            }
        }

        totalScore += currentFrameSum
    }

    printOutput(totalScore)
}

private fun computeBonus(rolls: Queue<Int>, howMany: Int) = rolls.take(howMany).sum()

private operator fun <E> Queue<E>.get(i: Int): E = elementAt(i)

private fun printOutput(totalScore: Int) {
    println(if (totalScore > 0) totalScore else "")
}

private fun readInput(args: Array<String>): List<Int> {
    val rolls = args[0]
            .split(",")
            .map(String::toInt)
    return rolls
}