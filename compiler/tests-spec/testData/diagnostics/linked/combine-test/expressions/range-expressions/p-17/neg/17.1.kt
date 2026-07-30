// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, range-expressions -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: chained 1..3..5 is illegal
 */

// TESTCASE NUMBER: 1
fun test() = 1..3 <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>..<!> 5
