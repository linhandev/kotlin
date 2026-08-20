// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 293 -> sentence 293
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 293 -> sentence 293
 * NUMBER: 1
 * DESCRIPTION: precise types for inner class must be constructed through an outer instance
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Outer {
    inner class Inner(val v: Int)
}

fun case_1() {
    val inner = Outer().Inner(2)
    inner checkType { check<Outer.Inner>() }
    inner.v checkType { check<Int>() }
    checkSubtype<Int>(Outer().Inner(2).v)
}
