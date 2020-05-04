package fnkt

import java.util.function.Consumer
import java.util.function.Predicate
import java.util.function.Supplier
import java.util.stream.Stream

fun<T> Consumer<T>.until(from: Iterator<T>, predicate: Predicate<T>) {
    do {
        val current = from.next();
        accept(current);
    } while (from.hasNext() && predicate.test(current));
}

fun<T> Consumer<T>.until(from: Iterable<T>, predicate: Predicate<T>)
    = until(from.iterator(), predicate)

fun<T> Consumer<T>.until(from: Supplier<T>, predicate: Predicate<T>)
    = until(from.asIterable(), predicate)

fun<T> Consumer<T>.until(from: Stream<T>, predicate: Predicate<T>)
    = until(from.iterator(), predicate)
