/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 316 -> sentence 316
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 316 -> sentence 316
 * NUMBER: 1
 * DESCRIPTION: anonymous object can implement a nested interface
 */

// TESTCASE NUMBER: 1
class Outer {
    interface Callback {
        fun on(): Int
    }
}

fun test(): Int = object : Outer.Callback {
    override fun on(): Int = 1
}.on()

fun box(): String {
    val cb = object : Outer.Callback {
        override fun on(): Int = 2
    }
    if (cb.on() != 2) return "NOK: cb"
    if (test() != 1) return "NOK: test"
    if (object : Outer.Callback { override fun on() = 3 }.on() != 3) return "NOK: anon"
    return "OK"
}
