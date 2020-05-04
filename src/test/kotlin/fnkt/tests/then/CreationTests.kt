package fnkt.tests.then

import junit.framework.Assert.assertNotNull
import org.junit.Test
import java.util.function.Consumer
import java.util.function.Function
import java.util.function.Supplier


class CreationTests {

    @Test
    fun canCreateRunnable() {
        val dummy = Runnable {}
        assertNotNull(dummy)
    }

    @Test
    fun canCreateSupplier() {
        val dummy = Supplier<Int> { 1 }
        assertNotNull(dummy)
    }

    @Test
    fun canCreateFunction() {
        val dummy = Function<Int, Int> { it + 1 }
        assertNotNull(dummy)
    }

    @Test
    fun canCreateConsumer() {
        val dummy = Consumer<Int> { println(it) }
        assertNotNull(dummy)
    }
}