// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 8 -> sentence 8
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: merged enum branches still require remaining constants for exhaustiveness
 */

// TESTCASE NUMBER: 1
enum class E { A, B, C }

fun case_1(e: E): Int = <!NO_ELSE_IN_WHEN!>when<!>(e) {
    E.A, E.B -> 1
}
