// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: exceptions, throwing-exceptions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: throw operand must be an exception type, not Int
 */

// TESTCASE NUMBER: 1
fun case_1(x: Int) {
    throw <!TYPE_MISMATCH!>x<!>
}
