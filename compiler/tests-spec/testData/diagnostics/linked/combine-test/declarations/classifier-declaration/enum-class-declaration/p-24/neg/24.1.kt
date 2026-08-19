// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 24 -> sentence 24
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: when on enum ordinal is not enum-exhaustive and Int subject needs else
 */

// TESTCASE NUMBER: 1
enum class E { A, B, C }

fun case_1(e: E): Int = <!NO_ELSE_IN_WHEN!>when<!>(e.ordinal) {
    0 -> 1
    1 -> 2
}
