// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: declarations, classifier-declaration, enum-class-declaration -> paragraph 10 -> sentence 10
 *                expressions, when-expressions -> paragraph 10 -> sentence 10
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: when used as statement still checks enum exhaustiveness (NO_ELSE_IN_WHEN)
 */

// TESTCASE NUMBER: 1
enum class Color { RED, GREEN, BLUE }

fun test(c: Color) {
    <!NO_ELSE_IN_WHEN!>when<!>(c) {
        Color.RED -> println(1)
        Color.GREEN -> println(2)
    }
}
