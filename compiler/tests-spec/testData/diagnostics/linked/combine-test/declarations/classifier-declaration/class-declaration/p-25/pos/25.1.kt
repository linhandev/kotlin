// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 25 -> sentence 25
 *                declarations, classifier-declaration, interface-declaration -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: class implements generic interface Sink of Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Sink<T> {
    fun accept(x: T)
}

class IntSink : Sink<Int> {
    override fun accept(x: Int) {}
}

fun case1() {
    checkSubtype<Sink<Int>>(IntSink())
}
