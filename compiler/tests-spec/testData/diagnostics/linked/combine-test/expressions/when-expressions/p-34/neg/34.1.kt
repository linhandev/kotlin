// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 34 -> sentence 34
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 34 -> sentence 34
 *                expressions, range-expressions -> paragraph 34 -> sentence 34
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 34 -> sentence 34
 * NUMBER: 1
 * DESCRIPTION: when expression with range branch only and without else is not exhaustive
 */

// TESTCASE NUMBER: 1
fun test(x: Int): String = <!NO_ELSE_IN_WHEN!>when<!>(x) {
    in 1..10 -> "small"
}
