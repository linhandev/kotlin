// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, comparison-expressions -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: comparison expressions cannot chain a less-than b less-than c
 */

// TESTCASE NUMBER: 1
fun case_1(a: Int, b: Int, c: Int): Boolean = a < b < <!TYPE_MISMATCH!>c<!>
