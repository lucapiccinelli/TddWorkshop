import com.g3.ktbowling.BowlingGame
import com.g3.ktbowling.Roll
import com.g3.ktbowling.RollImpl
import com.g3.ktbowling.main
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class BowlingGameTest{
    @TestFactory
    fun `as a bowling player, i want to know my total score`() = listOf(
            "1,1" to 2,
            "1" to -1,

            "5,5,1,1" to 13,
            "5,5,1" to -1,
            "5,5" to -1,

            "10,1,1" to 14,
            "10,1" to -1,
            "10" to -1,

            "1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1" to 20,
            "1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,5,5,1" to 29,
            "10,10,10,10,10,10,10,10,10,10,10,10" to 300,
            "9,0,9,0,9,0,9,0,9,0,9,0,9,0,9,0,9,0,9,0" to 90,
            "5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5" to 150
    ).map{ (input, expected) ->
        DynamicTest.dynamicTest("given this input list $input, i expect $expected"){
            val score = BowlingGame.playTheGame(RollImpl.fromCommaSeparatedString(input))
            Assertions.assertTrue(score.equals(expected))
        }
    }
}