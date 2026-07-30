// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 22 -> sentence 22
 *                type-system, introduction-1 -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: nullable receiver index reports UNSAFE_CALL
 */

// TESTCASE NUMBER: 1
fun test(xs: List<Int>?): Int = <!UNSAFE_CALL!>xs[0]<!>
