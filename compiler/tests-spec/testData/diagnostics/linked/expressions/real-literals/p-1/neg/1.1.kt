// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, real-literals -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: real literal 1.0d with unsupported d suffix is a syntax error
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x = 1.0<!UNRESOLVED_REFERENCE, UNSUPPORTED!>d<!><!SYNTAX!><!>
}
