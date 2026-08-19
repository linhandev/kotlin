// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 42 -> sentence 42
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 42 -> sentence 42
 *                syntax-and-grammar, syntax-grammar -> paragraph 42 -> sentence 42
 * NUMBER: 1
 * DESCRIPTION: in operator cannot be chained as a in b in c because intermediate result is not a valid contains receiver
 */

// TESTCASE NUMBER: 1
fun case1(): Boolean = 1 <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>in<!> 2 <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>in<!> 3
