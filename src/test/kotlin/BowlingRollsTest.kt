import com.g3.ktbowling.BowlingRolls
import com.g3.ktbowling.Roll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class BowlingRollsTest{

    @TestFactory
    fun `if i don't have enough rolls, i should stop processing`() = listOf(
            listOf(Roll(1)) to true,
            emptyList<Roll>() to false
    ).map{(inputRolls, expected) ->
        DynamicTest.dynamicTest("with this ${inputRolls} it is ${expected} that i can take a roll"){

            val rolls = BowlingRolls(inputRolls)
            val roll = rolls.takeNextRoll()

            Assertions.assertEquals(expected, roll.isNotEmpty())
        }
    }

    @Test
    fun `when you take a roll, it is removed from the list`(){
        val rolls = BowlingRolls(listOf(Roll(1)))
        rolls.takeNextRoll()

        Assertions.assertFalse(rolls.hasRolls())
    }

    @Test
    fun `assigning a bonus cumulates on the current roll value`(){
        val rolls = BowlingRolls(listOf(Roll(1)))
        val sumValue = rolls.assignBonus(1, 1)
        Assertions.assertEquals(true, sumValue.equals(2))
    }

    @TestFactory
    fun `to assign a bonus, i need to have enough rolls`() = listOf(
            listOf(Roll(1), Roll(1)) to true,
            listOf(Roll(1)) to false,
            emptyList<Roll>() to false
    ).map{(inputRolls, expected) ->
        DynamicTest.dynamicTest("with this ${inputRolls} it is ${expected} that i can assign a bonus"){

            val rolls = BowlingRolls(inputRolls)
            val score = rolls.assignBonus(0, 2)
            Assertions.assertEquals(expected, score.canContinue)
        }
    }

    @TestFactory
    fun `to say that i have a frame, i need a valid frame, but no more than 10 frames`()= listOf(
            Triple(listOf(Roll(1), Roll(1)), 0,true),
            Triple(listOf(Roll(10)), 0,true),
            Triple(listOf(Roll(1)), 0,true),
            Triple(emptyList<Roll>(), 0, false),

            Triple((0..11).map{Roll(10)}, 10,false),
            Triple((0..19).map{Roll(1)}, 10,false),
            Triple((0..19).map{Roll(1)}, 9,true)
    ).map{(inputRolls, times, expected) ->
        DynamicTest.dynamicTest("with this ${inputRolls} and taking out $times, it is $expected that i have more rolls"){

            val rolls = BowlingRolls(inputRolls)
            (0..times-1).forEach { rolls.takeNextFrame() }
            Assertions.assertEquals(expected, rolls.hasFrames())
        }
    }

}