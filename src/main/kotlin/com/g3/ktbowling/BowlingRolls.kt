package com.g3.ktbowling

import java.util.*

class BowlingRolls(rollsList: List<Roll>) {
    private val rollsQueue = ArrayDeque(rollsList)

    fun takeNextRoll(): Pair<Boolean, Roll> = rollsQueue
            .canTake(Roll(1), 1){it.remove()}

    fun hasRolls(): Boolean = rollsQueue.isNotEmpty()

    fun assignBonus(currentFrameValue: Int, howManyBonus: Int): Pair<Boolean, Int> = rollsQueue
            .canTake(-1, howManyBonus){
                currentFrameValue + it
                    .take(howManyBonus)
                    .sumBy { it.rollValue }
            }
}

private fun <E,R> ArrayDeque<E>.canTake(defaultValue: R, howMany: Int, function: (ArrayDeque<E>) -> R): Pair<Boolean, R> {
    return if(size >= howMany)
        Pair(true, function(this)) else
        Pair(false, defaultValue)
}
