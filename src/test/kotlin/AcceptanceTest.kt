import com.g3.ktbowling.main
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
    fun `given a list of comma separated numbers, i expect this result`() = listOf(
            "1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,5,5,1" to "",
            "1,4,4,5,6,4,5,5,10,0,1,7,3,6,4,10,2,8,6" to "133"
    ).map{ (input, expected) ->
        DynamicTest.dynamicTest("given this comma separated input list $input, i expect $expected"){
            eachTest {
                main(arrayOf(input))
                Assertions.assertEquals("$expected${System.lineSeparator()}", _myOut.toString())
            }
        }
    }
}