// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 295 -> sentence 295
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 295 -> sentence 295
 * NUMBER: 1
 * DESCRIPTION: precise types for inner class can be constructed directly inside an outer instance method
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Outer {
    inner class Inner(val v: Int)

    fun make(): Inner = Inner(1)
}

fun case_1() {
    val inner = Outer().make()
    inner checkType { check<Outer.Inner>() }
    inner.v checkType { check<Int>() }
    checkSubtype<Int>(Outer().make().v)
}
