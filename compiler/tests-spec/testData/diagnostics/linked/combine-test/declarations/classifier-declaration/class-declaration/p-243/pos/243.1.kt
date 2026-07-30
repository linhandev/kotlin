// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 243 -> sentence 243
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 243 -> sentence 243
 *                declarations, declarations-with-type-parameters, type-parameter-variance -> paragraph 243 -> sentence 243
 *                inheritance, inheriting -> paragraph 243 -> sentence 243
 * NUMBER: 1
 * DESCRIPTION: precise types when a subinterface fixes a covariant parent type argument and implementations widen on the read side
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Source<out T> {
    fun next(): T
}

interface IntSource : Source<Int>

class FixedIntSource : IntSource {
    override fun next(): Int = 7
}

fun case1() {
    val exact = FixedIntSource()
    exact checkType { check<FixedIntSource>() }
    checkSubtype<IntSource>(exact)
    val widened: Source<Number> = exact
    widened checkType { check<Source<Number>>() }
    widened.next() checkType { check<Number>() }
}

// TESTCASE NUMBER: 2
interface Producer<out T> {
    val current: T
}

interface StringProducer : Producer<String>

class FixedStringProducer(override val current: String) : StringProducer

fun case2() {
    val widened: Producer<Any> = FixedStringProducer("value")
    widened checkType { check<Producer<Any>>() }
    widened.current checkType { check<Any>() }
}

// TESTCASE NUMBER: 3
interface Factory<out T> {
    fun create(): T
}

interface BoolFactory : Factory<Boolean>

fun case3() {
    val widened: Factory<Any> = object : BoolFactory {
        override fun create(): Boolean = true
    }
    widened checkType { check<Factory<Any>>() }
    widened.create() checkType { check<Any>() }
}
