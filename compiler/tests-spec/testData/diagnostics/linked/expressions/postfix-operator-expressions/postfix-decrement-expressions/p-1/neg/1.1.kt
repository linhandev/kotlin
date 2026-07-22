// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, postfix-operator-expressions, postfix-decrement-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: postfix -- on non-assignable operand reports VARIABLE_EXPECTED
 */

// TESTCASE NUMBER: 1
fun case1() {
    <!VARIABLE_EXPECTED!>1<!>--
}
