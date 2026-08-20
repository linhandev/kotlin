// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 316 -> sentence 316
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 316 -> sentence 316
 * NUMBER: 1
 * DESCRIPTION: precise types for anonymous object can implement a nested interface
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Outer {
    interface Callback {
        fun on(): Int
    }
}

fun case_1() {
    val cb = object : Outer.Callback {
        override fun on(): Int = 1
    }
    checkSubtype<Outer.Callback>(cb)
    cb.on() checkType { check<Int>() }
}
