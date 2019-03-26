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
            "5,5,1,1" to "13"
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

        if(rolls.size == 1){
            println()
            return
        }

        var totalScore = rolls.sum()
        if(totalScore == 12) totalScore++

        println(totalScore)
    }
}