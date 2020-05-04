package fnkt.tests.then

import org.junit.Test
import fnkt.then
import java.util.function.Function
import kotlin.test.assertEquals

class SameClassChainTests {

    @Test
    fun runnablesCanBeChained() {
        var firstOk = false
        var secondOk = false
        val runnable = Runnable { println("1"); firstOk = true } then { println("2"); secondOk = true }
        runnable.run()
        assert(firstOk)
        assert(secondOk)
    }

    @Test
    fun functionsCanBeChained() {
        val fn = Function<Int, Int> { it + 1 } then(Function { it.toString() })
        assertEquals(
            fn.apply(2),
            "3" // (2 + 1).toString() == "3"
        )
    }
}