// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 154 -> sentence 154
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 159 -> sentence 159
 * NUMBER: 1
 * DESCRIPTION: modifier invalid modifier keyword
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p154.neg1

fun invalid<!SYNTAX!><!> <!SYNTAX!>case1<!><!SYNTAX!>(<!><!SYNTAX!>)<!> <!FUNCTION_DECLARATION_WITH_NO_NAME!><!SYNTAX!><!>{}<!>
