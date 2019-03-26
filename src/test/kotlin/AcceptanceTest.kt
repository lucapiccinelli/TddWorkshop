import org.junit.jupiter.api.*
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.io.PrintStream

class AcceptanceTest{
    private lateinit var _myOut: OutputStream

    fun setup(){
        _myOut = ByteArrayOutputStream()
        System.setOut(PrintStream(_myOut))
    }

    fun teardown(){
        _myOut.close()
    }

    inline fun eachTest(codeBlock: () -> Unit){
        try {
            setup()
            codeBlock()
        }finally {
            teardown()
        }
    }

    @TestFactory
    fun `as a bowling player, i want to know my total score`() = listOf(
            "1,1" to "2",
            "1" to "",

            "5,5,1,1" to "13",
            "5,5,1" to "",
            "5,5" to "",

            "10,1,1" to "14",
            "10,1" to "",
            "10" to "",

            "1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1" to "20",
            "1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,5,5,1" to "29",
            "10,10,10,10,10,10,10,10,10,10,10,10" to "300",

            "1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,5,5,1" to "",
            "1,4,4,5,6,4,5,5,10,0,1,7,3,6,4,10,2,8,6" to "133"
    ).map{ (input, expected) ->
        DynamicTest.dynamicTest("given this comma separated input list $input, i expect $expected"){
            eachTest {
                play(arrayOf(input))
                Assertions.assertEquals("$expected${System.lineSeparator()}", _myOut.toString())
            }
        }
    }

    private fun play(args: Array<String>) {
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
}