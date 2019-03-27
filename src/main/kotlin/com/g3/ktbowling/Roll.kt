package com.g3.ktbowling

class Roll(val rollValue: Int) {
    operator fun plus(value: Int): FrameScore =
        when(isEmpty()){
            true -> EmptyFrameScore()
            else -> FrameScoreWithValue(rollValue + value)
        }

    fun isNotEmpty(): Boolean = !isEmpty()
    private fun isEmpty(): Boolean = rollValue < 0


    val isAStrike: Boolean = rollValue == 10

    companion object {
        fun fromCommaSeparatedString(inputStr: String) : List<Roll> =
                inputStr.split(",")
                        .map(String::toInt)
                        .map { Roll(it) }
    }
}