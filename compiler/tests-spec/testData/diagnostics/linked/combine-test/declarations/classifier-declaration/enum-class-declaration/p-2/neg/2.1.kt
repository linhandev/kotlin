// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 2 -> sentence 2
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: when expression on enum missing a constant is not exhaustive
 */

// TESTCASE NUMBER: 1
enum class Color { RED, GREEN, BLUE }

fun case_1(c: Color): String = <!NO_ELSE_IN_WHEN!>when<!>(c) {
    Color.RED -> "r"
    Color.GREEN -> "g"
}
