// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, call-and-property-access-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: f( with missing closing parenthesis reports SYNTAX
 */

fun f(): Int = 1

// TESTCASE NUMBER: 1
fun case1() { val x = f( <!SYNTAX!><!>}
