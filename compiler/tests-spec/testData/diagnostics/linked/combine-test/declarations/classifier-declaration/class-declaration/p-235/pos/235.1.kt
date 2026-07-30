// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 235 -> sentence 235
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 235 -> sentence 235
 *                declarations, declarations-with-type-parameters, type-parameter-variance -> paragraph 235 -> sentence 235
 *                inheritance, inheriting -> paragraph 235 -> sentence 235
 * NUMBER: 1
 * DESCRIPTION: precise types after read-side widening of class and anonymous-object implementations of covariant generic interfaces
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Producer<out T> {
    fun produce(): T
}

class IntProducer : Producer<Int> {
    override fun produce(): Int = 7
}

fun case1() {
    val exact = IntProducer()
    exact checkType { check<IntProducer>() }
    val widened: Producer<Number> = exact
    widened checkType { check<Producer<Number>>() }
    widened.produce() checkType { check<Number>() }
}

// TESTCASE NUMBER: 2
interface Source<out T> {
    val current: T
}

class StringSource(override val current: String) : Source<String>

fun case2() {
    val widened: Source<Any> = StringSource("value")
    widened checkType { check<Source<Any>>() }
    widened.current checkType { check<Any>() }
}

// TESTCASE NUMBER: 3
interface Factory<out T> {
    fun create(): T
}

fun case3() {
    val widened: Factory<CharSequence> = object : Factory<String> {
        override fun create(): String = "anonymous"
    }
    widened checkType { check<Factory<CharSequence>>() }
    widened.create() checkType { check<CharSequence>() }
}
