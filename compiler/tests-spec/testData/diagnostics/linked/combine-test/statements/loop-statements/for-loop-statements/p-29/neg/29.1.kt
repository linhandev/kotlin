// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: statements, loop-statements -> paragraph 29 -> sentence 29
 *                expressions, range-expressions -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: for loop is a statement and cannot be used as an expression
 */

// TESTCASE NUMBER: 1
fun test(): Int = <!EXPRESSION_EXPECTED!>for (x in listOf(1)) x<!>
