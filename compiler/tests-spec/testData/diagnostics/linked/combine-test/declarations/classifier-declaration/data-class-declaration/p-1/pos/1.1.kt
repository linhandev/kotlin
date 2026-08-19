// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 1 -> sentence 1
 *                declarations, classifier-declaration, data-class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: data class generates component1/component2 for destructuring
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Pt(val x: Int, val y: Int)

fun case_1(p: Pt) {
    val (a, b) = p
    checkSubtype<Int>(a + b)
}
