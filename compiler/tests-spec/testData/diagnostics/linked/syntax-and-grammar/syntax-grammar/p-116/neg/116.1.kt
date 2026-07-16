// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 116 -> sentence 116
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 113 -> sentence 113
 * NUMBER: 1
 * DESCRIPTION: lineStringExpression unclosed template brace
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p116.neg1

fun case1() { val s = "${1<!SYNTAX!><!>" }<!SYNTAX!><!>
