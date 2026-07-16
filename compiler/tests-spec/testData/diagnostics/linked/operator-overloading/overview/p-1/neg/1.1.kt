// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: operator-overloading, overview -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: operator syntax without matching operator function reports UNRESOLVED_REFERENCE_WRONG_RECEIVER
 */

// TESTCASE NUMBER: 1
class A9011
class B9011

fun case_1() {
    val a = A9011()
    val b = B9011()
    a <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>+<!> b
}
