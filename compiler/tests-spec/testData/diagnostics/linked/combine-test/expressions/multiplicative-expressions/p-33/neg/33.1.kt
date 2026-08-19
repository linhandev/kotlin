// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 33 -> sentence 33
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 33 -> sentence 33
 *                type-system, built-in-integer-types -> paragraph 33 -> sentence 33
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 33 -> sentence 33
 * NUMBER: 1
 * DESCRIPTION: String literal cannot be left operand of times with Long in multiplicative expression
 */

// TESTCASE NUMBER: 1
fun case_1() = "3" <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>*<!> 2L
