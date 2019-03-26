import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.io.PrintStream

class AcceptanceTest{
    private lateinit var _myOut: OutputStream

    @BeforeEach
    fun setup(){
        _myOut = ByteArrayOutputStream()
        System.setOut(PrintStream(_myOut))
    }

    @AfterEach
    fun teardown(){
        _myOut.close()
    }

    @Test
    fun `as a bowling player, i want to know my total score`(){
        play(arrayOf("1,1"))
        Assertions.assertEquals("2${System.lineSeparator()}", _myOut.toString())
    }
}