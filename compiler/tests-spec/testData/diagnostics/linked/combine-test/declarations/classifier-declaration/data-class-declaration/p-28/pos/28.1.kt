// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: destructuring combines with when expression
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Pt(val x: Int, val y: Int)

fun case_1(p: Pt) {
    val (x, y) = p
    checkSubtype<Int>(when {
        else -> x + y
    })
}
