// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: top-level default arguments cannot forward-reference later parameters
 */

// TESTCASE NUMBER: 1
fun f(a: Int = <!UNINITIALIZED_PARAMETER!>b<!>, b: Int = 1): Int = a + b
