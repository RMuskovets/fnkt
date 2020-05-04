package fnkt.tests.then

import org.junit.Test
import fnkt.then
import java.util.function.Consumer
import java.util.function.Function
import java.util.function.Supplier
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class InteropTests {

    @Test
    fun runnableCanBeChainedWithSupplier() {
        var counter = 0
        val runnable = Runnable { println("1"); counter++ }
        val supplier = Supplier { counter }
        val chained  = runnable then supplier
        val result   = chained.get()
        assertNotNull(chained)
        assertEquals(counter, result)
    }

    @Test
    fun supplierCanBeChainedWithFunction() {
        var counter = 0
        val supplier = Supplier { ++counter }
        val function = Function<Int, Int> { it + 1 }
        val chained  = supplier then function
        val result   = chained.get()
        assertNotNull(chained)
        assertEquals(counter + 1, result)
    }

    @Test
    fun supplierCanBeChainedWithConsumer() {
        var counter = 0
        var lastValue = -1
        val supplier = Supplier { ++counter }
        val consumer = Consumer<Int> { println(it); lastValue = it } // i thought it'll infer it's type from lastValue
        val chained  = supplier then consumer
        chained.run()
        assertNotNull(chained)
        assertEquals(lastValue, counter)
    }
}