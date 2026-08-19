// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, range-expressions -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 14 -> sentence 14
 *                type-system, introduction-1 -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: null literal cannot be operand of ..
 */

// TESTCASE NUMBER: 1
fun test() = null <!UNSAFE_OPERATOR_CALL!>..<!> 1
