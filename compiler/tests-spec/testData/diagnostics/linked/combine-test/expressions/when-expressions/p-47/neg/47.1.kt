// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 47 -> sentence 47
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 47 -> sentence 47
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 47 -> sentence 47
 * NUMBER: 1
 * DESCRIPTION: when expression with is branch only and without else is not exhaustive
 */

// TESTCASE NUMBER: 1
fun test(x: Any): Int = <!NO_ELSE_IN_WHEN!>when<!>(x) {
    is String -> x.length
}
