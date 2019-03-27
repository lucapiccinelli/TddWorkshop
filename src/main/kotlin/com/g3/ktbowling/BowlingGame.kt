package com.g3.ktbowling

class BowlingGame {
    companion object {
        fun playTheGame(rollsList: List<Roll>): FrameScore {
            val rolls = BowlingRolls(rollsList)
            var totalScore: FrameScore = FrameScoreWithValue()

            while (rolls.hasFrames() && totalScore.canContinue) {
                val frame: BowlingFrame = rolls.takeNextFrame()
                totalScore += frame.computeFrameScore()
            }
            return totalScore
        }
    }
}
