// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: declarations, classifier-declaration, data-class-declaration -> paragraph 21 -> sentence 21
 *                declarations, classifier-declaration, class-declaration -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: nested data class still generates componentN
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Outer {
    data class Inner(val v: Int)
}

fun case_1() {
    val (x) = Outer.Inner(1)
    checkSubtype<Int>(x)
}
