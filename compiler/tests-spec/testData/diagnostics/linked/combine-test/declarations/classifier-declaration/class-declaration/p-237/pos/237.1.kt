// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 237 -> sentence 237
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 237 -> sentence 237
 *                declarations, declarations-with-type-parameters, type-parameter-variance -> paragraph 237 -> sentence 237
 *                inheritance, inheriting -> paragraph 237 -> sentence 237
 * NUMBER: 1
 * DESCRIPTION: precise types after write-side narrowing of class and anonymous-object implementations of contravariant generic interfaces
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Consumer<in T> {
    fun accept(value: T)
}

class NumberConsumer : Consumer<Number> {
    override fun accept(value: Number) {}
}

fun case1() {
    val exact = NumberConsumer()
    exact checkType { check<NumberConsumer>() }
    val narrowed: Consumer<Int> = exact
    narrowed checkType { check<Consumer<Int>>() }
}

// TESTCASE NUMBER: 2
interface Sink<in T> {
    fun process(item: T)
}

class AnySink : Sink<Any> {
    override fun process(item: Any) {}
}

fun case2() {
    val narrowed: Sink<String> = AnySink()
    narrowed checkType { check<Sink<String>>() }
}

// TESTCASE NUMBER: 3
interface Handler<in T> {
    fun handle(event: T)
}

fun case3() {
    val narrowed: Handler<CharSequence> = object : Handler<Any> {
        override fun handle(event: Any) {}
    }
    narrowed checkType { check<Handler<CharSequence>>() }
}
