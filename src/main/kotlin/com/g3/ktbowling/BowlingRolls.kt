package com.g3.ktbowling

import java.util.*

class BowlingRolls(rollsList: List<Roll>) {
    private val rollsQueue = ArrayDeque(rollsList)
    private var _frameCount = 0

    fun takeNextRoll(): Roll = rollsQueue
            .canTake(Roll(-1), 1){it.remove()}

    fun hasRolls(): Boolean = rollsQueue.isNotEmpty()

    fun assignBonus(currentFrameValue: Int, howManyBonus: Int): FrameScore = rollsQueue
            .canTake(EmptyFrameScore(), howManyBonus){
                FrameScoreWithValue(currentFrameValue + it
                    .take(howManyBonus)
                    .sumBy { it.rollValue })
            }


    fun hasFrames(): Boolean {
        return hasRolls() && _frameCount < 10
    }

    fun takeNextFrame(): BowlingFrame{
        _frameCount++

        val firstRoll: Roll = takeNextRoll()
        var frameScore: FrameScore = FrameScoreWithValue(firstRoll.rollValue)

        if (firstRoll.isAStrike) {
            return StrikeFrame(frameScore, this)
        } else {
            frameScore += takeNextRoll()
            if (frameScore.IsASpare) {
                return SpareFrame(frameScore, this)
            }
            return StandardFrame(frameScore)
        }
    }
}

private fun <E,R> ArrayDeque<E>.canTake(defaultValue: R, howMany: Int, function: (ArrayDeque<E>) -> R): R {
    return if(size >= howMany)
        function(this) else
        defaultValue
}
