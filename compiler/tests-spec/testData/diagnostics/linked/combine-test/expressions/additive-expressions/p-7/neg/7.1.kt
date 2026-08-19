// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, additive-expressions -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: asymmetric plus: A plus B works but B plus A requires separate overload
 */

// TESTCASE NUMBER: 1
class A
class B

operator fun A.plus(b: B): A = A()

fun case_1_ok(): A = A() + B()

fun case_1() = B() <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>+<!> A()
