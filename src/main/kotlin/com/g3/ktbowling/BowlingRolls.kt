package com.g3.ktbowling

import java.util.*

class BowlingRolls(rollsList: List<Roll>) {
    private val rollsQueue = ArrayDeque(rollsList)

    fun takeNextRoll(): Roll = rollsQueue
            .canTake(Roll(-1), 1){it.remove()}

    fun hasRolls(): Boolean = rollsQueue.isNotEmpty()

    fun assignBonus(currentFrameValue: Int, howManyBonus: Int): FrameScore = rollsQueue
            .canTake(EmptyFrameScore(), howManyBonus){
                FrameScoreWithValue(currentFrameValue + it
                    .take(howManyBonus)
                    .sumBy { it.rollValue })
            }
}

private fun <E,R> ArrayDeque<E>.canTake(defaultValue: R, howMany: Int, function: (ArrayDeque<E>) -> R): R {
    return if(size >= howMany)
        function(this) else
        defaultValue
}
