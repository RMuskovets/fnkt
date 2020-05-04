package fnkt.tests.supplier

import fnkt.skipIf
import fnkt.until
import org.junit.Test
import java.util.function.Supplier
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class SupplierTests {

    @Test
    fun canCreateSupplier() {
        assertNotNull(Supplier { 1 })
    }

    @Test
    fun canSupplyUntil() {
        assertNotNull(Supplier { 1 } until { false })
    }

    @Test
    fun canSkipIf() {
        assertNotNull(Supplier { 1 } skipIf { true })
    }

    @Test
    fun canChainSUAndSI() {
        assertNotNull(Supplier { 1 } skipIf { false } until { false })
    }

    @Test
    fun supplierDoesSupply() {
        assertEquals(1, Supplier { 1 }.get())
    }

    @Test
    fun supplyUntilFalseReturnsEmptyIterable() {
        assert(!((Supplier { 1 } until { false }).iterator().hasNext()))
    }

    @Test
    fun supplyUntilTrueReturnsNonEmptyIterable() {
        assert((Supplier { 1 } until { true }).iterator().hasNext())
    }
}