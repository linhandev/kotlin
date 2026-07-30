// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: underscore skips a destructuring component
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Pt(val x: Int, val y: Int)

fun case_1(p: Pt) {
    val (x, _) = p
    checkSubtype<Int>(x)
}
