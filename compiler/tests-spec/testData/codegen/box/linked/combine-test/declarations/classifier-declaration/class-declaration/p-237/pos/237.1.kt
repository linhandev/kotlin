// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 237 -> sentence 237
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 237 -> sentence 237
 *                declarations, declarations-with-type-parameters, type-parameter-variance -> paragraph 237 -> sentence 237
 *                inheritance, inheriting -> paragraph 237 -> sentence 237
 * NUMBER: 1
 * DESCRIPTION: classes and anonymous objects implementing contravariant consumer interfaces can be narrowed on the write side while maintaining correct runtime behavior; contrasts with p-235 covariant producers and p-232 invariant consumers
 */

// TESTCASE NUMBER: 1
interface Consumer<in T> {
    fun accept(value: T)
}

class NumberConsumer : Consumer<Number> {
    var received: Double = 0.0
    override fun accept(value: Number) {
        received = value.toDouble()
    }
}

// TESTCASE NUMBER: 2
interface Sink<in T> {
    fun process(item: T)
}

class AnySink : Sink<Any> {
    var count: Int = 0
    override fun process(item: Any) {
        count++
    }
}

// TESTCASE NUMBER: 3
interface Handler<in T> {
    fun handle(event: T): String
}

fun box(): String {
    val intConsumer: Consumer<Int> = NumberConsumer()
    intConsumer.accept(42)
    if ((intConsumer as NumberConsumer).received != 42.0) return "NOK: number-consumer"

    val stringSink: Sink<String> = AnySink()
    stringSink.process("test")
    if ((stringSink as AnySink).count != 1) return "NOK: any-sink"

    val charSeqHandler: Handler<CharSequence> = object : Handler<Any> {
        override fun handle(event: Any): String = "handled-$event"
    }
    if (charSeqHandler.handle("test") != "handled-test") return "NOK: anonymous-handler"
    
    return "OK"
}
