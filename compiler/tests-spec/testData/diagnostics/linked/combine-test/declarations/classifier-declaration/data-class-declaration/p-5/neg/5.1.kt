// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: destructuring with more components than data class provides fails
 */

// TESTCASE NUMBER: 1
data class Pt(val x: Int, val y: Int)

fun case_1(p: Pt): Int {
    val (a, b, c) = <!COMPONENT_FUNCTION_MISSING!>p<!>
    return a
}
