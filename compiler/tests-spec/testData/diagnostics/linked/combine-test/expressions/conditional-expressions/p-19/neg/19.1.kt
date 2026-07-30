// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 18 -> sentence 18
 *                expressions, elvis-operator-expressions -> paragraph 18 -> sentence 18
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: conditional expression condition with Elvis operator still nullable cannot be used as Boolean condition
 */

// TESTCASE NUMBER: 1
fun test(x: Boolean?): Int = if (<!TYPE_MISMATCH!>x <!USELESS_ELVIS_RIGHT_IS_NULL!>?: null<!><!>) 1 else 0
