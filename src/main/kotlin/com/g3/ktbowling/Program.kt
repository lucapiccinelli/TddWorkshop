package com.g3.ktbowling

fun main(args: Array<String>) {
    val rolls = args[0]
            .split(",")
            .map(String::toInt)

    var i = 0
    var totalScore = 0
    var currentFrameIndex = 0
    while (i < rolls.size && currentFrameIndex++ < 10){
        var currentFrameSum = rolls[i]
        i++

        if(currentFrameSum == 10){
            if(rolls.size > i + 1){
                currentFrameSum += rolls[i] + rolls[i + 1]
            }
            else{
                totalScore = -1
                break
            }
        }
        else{
            if (rolls.size > i){
                currentFrameSum += rolls[i]
                i++
            }
            else{
                totalScore = -1
                break
            }
            if(currentFrameSum == 10){
                if (rolls.size > i){
                    currentFrameSum += rolls[i]
                }
                else{
                    totalScore = -1
                    break
                }
            }
        }

        totalScore += currentFrameSum
    }
    println(if(totalScore > 0) totalScore else "")
}