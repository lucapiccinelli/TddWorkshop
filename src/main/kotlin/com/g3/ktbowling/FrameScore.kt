package com.g3.ktbowling

interface FrameScore {
    operator fun plus(takeNextRoll: Roll) : FrameScore
    operator fun plus(score: FrameScore) : FrameScore
    operator fun plus(value: Int) : FrameScore
    override operator fun equals(other: Any?) : Boolean
    val IsASpare: Boolean
    val canContinue: Boolean
    override fun toString() : String
}

class FrameScoreWithValue(var value: Int = 0) : FrameScore {
    override fun toString(): String = "$value"

    override fun equals(other: Any?): Boolean = value == other

    override val canContinue: Boolean = true

    override fun plus(score: FrameScore): FrameScore {
        return score + value
    }

    override fun plus(roll: Roll) : FrameScore{
        return roll + value
    }

    override fun plus(outValue: Int) : FrameScore{
        return FrameScoreWithValue(outValue + value)
    }

    override val IsASpare: Boolean = value == 10
}


class EmptyFrameScore : FrameScore {
    override fun toString(): String = ""
    override fun equals(other: Any?): Boolean = this === other
    override val canContinue: Boolean = false
    override fun plus(score: FrameScore): FrameScore = EmptyFrameScore()
    override fun plus(outValue: Int): FrameScore = EmptyFrameScore()
    override fun plus(takeNextRoll: Roll): FrameScore = EmptyFrameScore()
    override val IsASpare: Boolean = false
}
