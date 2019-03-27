package com.g3.ktbowling

interface Roll {
    val rollValue: Int
    val isAStrike: Boolean
    operator fun plus(value: Int): FrameScore
    fun isNotEmpty(): Boolean
    fun isEmpty(): Boolean
}

class RollImpl(override val rollValue: Int) : Roll {
    override operator fun plus(value: Int): FrameScore = FrameScoreWithValue(rollValue + value)
    override fun isNotEmpty(): Boolean = !isEmpty()
    override fun isEmpty(): Boolean = rollValue < 0
    override val isAStrike: Boolean = rollValue == 10

    companion object {
        fun fromCommaSeparatedString(inputStr: String) : List<Roll> =
                inputStr.split(",")
                        .map(String::toInt)
                        .map { RollImpl(it) }
    }
}

class EmptyRoll : Roll{
    override val rollValue = -1
    override val isAStrike = false
    override fun isNotEmpty() = false
    override fun isEmpty() = true
    override fun plus(value: Int): FrameScore = EmptyFrameScore()
}