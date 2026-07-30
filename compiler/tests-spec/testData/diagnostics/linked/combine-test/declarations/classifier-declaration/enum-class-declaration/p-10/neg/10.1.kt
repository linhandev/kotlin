// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 10 -> sentence 10
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 10 -> sentence 10
 *                type-system, introduction-1 -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: nullable enum when expression requires a null branch for exhaustiveness
 */

// TESTCASE NUMBER: 1
enum class E { A, B }

fun case_1(e: E?): String = <!NO_ELSE_IN_WHEN!>when<!>(e) {
    E.A -> "a"
    E.B -> "b"
}
