// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, call-and-property-access-expressions -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: trailing dot without member name after string is a syntax error
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x = "".<!SYNTAX!><!>
}
