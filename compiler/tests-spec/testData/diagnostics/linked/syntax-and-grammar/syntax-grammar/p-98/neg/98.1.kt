// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 98 -> sentence 98
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 99 -> sentence 99
 * NUMBER: 1
 * DESCRIPTION: assignableExpression assign to expression literal
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p98.neg1

fun case1() { (<!VARIABLE_EXPECTED!>1 + 2<!>) = 3 }
