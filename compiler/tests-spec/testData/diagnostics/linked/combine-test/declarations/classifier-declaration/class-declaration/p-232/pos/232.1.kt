// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 232 -> sentence 232
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 232 -> sentence 232
 *                inheritance, inheriting -> paragraph 232 -> sentence 232
 * NUMBER: 1
 * DESCRIPTION: type inference when class type arguments on a consumer generic interface match accept parameter types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Sink<T> {
    fun accept(x: T)
}

class IntSink : Sink<Int> {
    var last: Int = 0
    override fun accept(x: Int) {
        last = x
    }
}

fun case1() {
    val s = IntSink()
    s checkType { check<IntSink>() }
    checkSubtype<Sink<Int>>(s)
    s.accept(1)
    s.last checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
class StringSink : Sink<String> {
    var last: String = ""
    override fun accept(x: String) {
        last = x
    }
}

fun case2() {
    val s = StringSink()
    s checkType { check<StringSink>() }
    checkSubtype<Sink<String>>(s)
    s.accept("ok")
    s.last checkType { check<String>() }
}

// TESTCASE NUMBER: 3
class RecordingSink<T>(initial: T) : Sink<T> {
    var last: T = initial
    override fun accept(x: T) {
        last = x
    }
}

fun case3() {
    val s = RecordingSink(0L)
    s checkType { check<RecordingSink<Long>>() }
    checkSubtype<Sink<Long>>(s)
    s.accept(10L)
    s.last checkType { check<Long>() }
}
