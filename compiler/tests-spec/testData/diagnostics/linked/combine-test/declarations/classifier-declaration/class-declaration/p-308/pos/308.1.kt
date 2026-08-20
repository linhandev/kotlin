// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 308 -> sentence 308
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 308 -> sentence 308
 * NUMBER: 1
 * DESCRIPTION: precise types for multi-level nested qualification via outer.middle.inner
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Outer {
    class Middle {
        class Inner(val v: Int)
    }
}

fun case_1() {
    val inner = Outer.Middle.Inner(1)
    inner checkType { check<Outer.Middle.Inner>() }
    inner.v checkType { check<Int>() }
}
