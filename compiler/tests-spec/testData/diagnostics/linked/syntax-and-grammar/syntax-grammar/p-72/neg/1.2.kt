// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 72 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: forStatement missing closing parenthesis
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p72.neg2

fun case1() { for (i in 1..<!TYPE_MISMATCH!><!FUNCTION_EXPECTED!>2<!> { }<!><!SYNTAX!><!> <!SYNTAX!><!>}
