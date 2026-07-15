// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 64 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: parenthesizedUserType missing opening parenthesis
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p64.neg3

<!NON_MEMBER_FUNCTION_NO_BODY!>fun <T> case1(x: T)<!> <!SYNTAX!>&<!> <!SYNTAX!>Any<!><!SYNTAX!>)<!><!SYNTAX!>:<!> <!SYNTAX!>T<!> <!SYNTAX!>&<!> <!SYNTAX!>Any<!> <!SYNTAX!>=<!> <!SYNTAX!>x<!>
