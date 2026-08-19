// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: declarations, classifier-declaration, enum-class-declaration -> paragraph 7 -> sentence 7
 *                type-system, introduction-1 -> paragraph 7 -> sentence 7
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: when expression with nullable enum subject missing null branch and without else is not exhaustive
 */

// TESTCASE NUMBER: 1
enum class Color { RED, GREEN }

fun test(c: Color?): Int = <!NO_ELSE_IN_WHEN!>when<!>(c) {
    Color.RED -> 1
    Color.GREEN -> 2
}
