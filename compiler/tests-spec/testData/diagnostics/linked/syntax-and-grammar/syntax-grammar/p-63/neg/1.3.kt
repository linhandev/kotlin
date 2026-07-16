// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 63 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: receiverType parenthesizedType missing closing parenthesis
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p63.neg3

val value: (String<!SYNTAX!><!> <!SYNTAX!>-><!><!SYNTAX!><!> Unit = { "" }
