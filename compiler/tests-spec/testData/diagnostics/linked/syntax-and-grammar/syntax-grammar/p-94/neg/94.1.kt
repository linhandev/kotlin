// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 94 -> sentence 94
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 108 -> sentence 108
 * syntax-and-grammar, syntax-grammar -> paragraph 95 -> sentence 95
 * NUMBER: 1
 * DESCRIPTION: postfixUnaryExpression literal trailing call parenthesis
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p94.neg1

fun case1() { val x = <!FUNCTION_EXPECTED!>1<!>( <!SYNTAX!><!>}
