// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 78 -> sentence 78
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 79 -> sentence 79
 * NUMBER: 1
 * DESCRIPTION: expression empty parentheses missing subexpression
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p78.neg1

fun case1() { val y = 1 + (<!SYNTAX!><!>) }
