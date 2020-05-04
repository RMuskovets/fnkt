@file:JvmName("Then")

package fnkt

import java.util.function.Consumer
import java.util.function.Supplier
import java.util.function.Function

infix fun Runnable.then(then: Runnable): Runnable {
    val self = this
    return Runnable {
        self.run()
        then.run()
    }
}
infix fun Runnable.then(then: () -> Unit)
        = this then Runnable(then)

infix fun<S, I, D> Function<S, I>.then(then: Function<I, D>): Function<S, D> {
    val self = this
    return Function { then.apply(self.apply(it)) }
}
infix fun<S, I, D> Function<S, I>.then(then: (source: I) -> D)
        = this then Function(then)

infix fun<S, D> Function<S, D>.then(then: Consumer<D>): Consumer<S> {
    val self = this
    return Consumer { then.accept(self.apply(it)) }
}
infix fun<S, D> Function<S, D>.then(then: (arg: D) -> Unit)
        = this then Consumer(then)

infix fun<T> Supplier<T>.then(then: Consumer<T>): Runnable {
    val self = this
    return Runnable {
        then.accept(self.get())
    }
}

infix fun<T> Runnable.then(then: Supplier<T>): Supplier<T> {
    val self = this
    return Supplier {
        self.run()
        return@Supplier then.get()
    }
}

infix fun<S, D> Supplier<S>.then(then: Function<S, D>): Supplier<D> {
    val self = this
    return Supplier { then.apply(self.get()) }
}