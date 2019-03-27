package com.g3.ktbowling

interface BowlingFrame {
    fun computeFrameScore(): FrameScore
}



class StandardFrame(val frameScore: FrameScore) : BowlingFrame {
    override fun computeFrameScore(): FrameScore = frameScore
}

class SpareFrame(val frameScore: FrameScore, val bowlingRolls: BowlingRolls) : BowlingFrame {
    override fun computeFrameScore(): FrameScore = frameScore + bowlingRolls.assignBonus(0, 1)
}

class StrikeFrame(val frameScore: FrameScore, val bowlingRolls: BowlingRolls) : BowlingFrame {
    override fun computeFrameScore(): FrameScore = frameScore + bowlingRolls.assignBonus(0, 2)
}
