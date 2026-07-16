// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 72 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: forStatement missing in keyword
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p72.neg1

fun case1() { <!EXPRESSION_EXPECTED!>for (i <!SYNTAX!>1<!><!><!DEBUG_INFO_MISSING_UNRESOLVED!><!SYNTAX!><!>..<!>2<!SYNTAX!>)<!> <!UNUSED_LAMBDA_EXPRESSION!>{ }<!> }
