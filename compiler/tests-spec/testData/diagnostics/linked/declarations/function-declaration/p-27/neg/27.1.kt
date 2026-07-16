// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: infix modifier requires a member or extension function with exactly one value parameter
 */

// TESTCASE NUMBER: 1
<!INAPPLICABLE_INFIX_MODIFIER!>infix<!> fun Int.multiParam(x: Int, y: Int): Int = x + y

// TESTCASE NUMBER: 2
<!INAPPLICABLE_INFIX_MODIFIER!>infix<!> fun nonMemberInfix(x: Int, y: Int): Int = x + y
