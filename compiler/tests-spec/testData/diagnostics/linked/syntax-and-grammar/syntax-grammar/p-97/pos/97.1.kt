// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 97 -> sentence 97
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 96 -> sentence 96
 * syntax-and-grammar, syntax-grammar -> paragraph 100 -> sentence 100
 * syntax-and-grammar, syntax-grammar -> paragraph 101 -> sentence 101
 * NUMBER: 1
 * DESCRIPTION: parenthesizedDirectlyAssignableExpression paren assign target
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p97.pos1

fun case1() { var a = intArrayOf(0); (a)[0] = 1 }
