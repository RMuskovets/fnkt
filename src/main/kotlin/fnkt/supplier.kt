package fnkt

import java.util.function.Predicate
import java.util.function.Supplier

/**
 * @param predicate The predicate to test on each value
 */
infix fun<T> Supplier<T>.until(predicate: Predicate<T>): Iterable<T> =
        Iterable { object : Iterator<T> {
            private var current: T = get()

            override fun hasNext(): Boolean = predicate.test(current)
            override fun next(): T {
                val prev = current
                current = get()
                return prev
            }
        }}

infix fun<T> Supplier<T>.skipIf(predicate: Predicate<T>): Supplier<T> =
        Supplier {
            var result: T
            do {
                result = get()
            } while (predicate.test(result))
            return@Supplier result
        }

fun<T> Supplier<T>.asIterable(): Iterable<T> = Iterable { object : Iterator<T> {
    override fun hasNext(): Boolean = true
    override fun next() = get()
}}