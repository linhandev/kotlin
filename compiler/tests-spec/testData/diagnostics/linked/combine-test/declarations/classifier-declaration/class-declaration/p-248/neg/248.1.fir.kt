// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 248 -> sentence 248
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 248 -> sentence 248
 *                declarations, declarations-with-type-parameters, type-parameter-variance -> paragraph 248 -> sentence 248
 * NUMBER: 1
 * DESCRIPTION: star-projected interface consumer members cannot be written; differs from p-13/p-139 class property SETTER_PROJECTED_OUT and from p-247 star-projected reads
 */

// TESTCASE NUMBER: 1
interface Mut<T> {
    fun set(x: T)
}

class MutImpl<T>(var v: T) : Mut<T> {
    override fun set(x: T) { v = x }
}

fun case1(m: Mut<*>) {
    m.set(<!ARGUMENT_TYPE_MISMATCH!>1<!>)
}

fun case1ViaClass() {
    case1(MutImpl(0))
}

// TESTCASE NUMBER: 2
interface Sink<T> {
    fun accept(value: T)
}

class SinkImpl<T>(var last: T? = null) : Sink<T> {
    override fun accept(value: T) { last = value }
}

fun case2(s: Sink<*>) {
    s.accept(<!TYPE_MISMATCH!>"x"<!>)
}

fun case2ViaClass() {
    case2(SinkImpl<String>())
}

// TESTCASE NUMBER: 3
interface Slot<T> {
    var value: T
}

class SlotImpl<T>(override var value: T) : Slot<T>

fun case3(slot: Slot<*>) {
    <!SETTER_PROJECTED_OUT!>slot.value<!> = true
}

fun case3ViaClass() {
    case3(SlotImpl(false))
}
