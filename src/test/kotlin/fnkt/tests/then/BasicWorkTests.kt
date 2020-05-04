package fnkt.tests.then

import org.junit.Test
import java.util.function.Consumer
import java.util.function.Function
import java.util.function.Supplier
import kotlin.test.assertEquals

class BasicWorkTests {

    @Test
    fun runnableDoesExecute() {
        var executed = false
        Runnable { executed = true }.run()
        assert(executed)
    }

    @Test
    fun functionDoesMap() {
        assertEquals(
            2,
            Function<Int, Int>{ it + 1 }.apply(1)
        )
    }

    @Test
    fun supplierDoesSupply() {
        assertEquals(
            1,
            Supplier { 1 }.get()
        )
    }

    @Test
    fun consumerDoesConsume() {
        var consumed = false
        var value: Int? = null
        Consumer<Int> { println(it); consumed = true; value = it }
            .accept(1)
        assert(consumed)
        assertEquals(value, 1)
    }
}