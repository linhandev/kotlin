// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 99 -> sentence 99
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 98 -> sentence 98
 * syntax-and-grammar, syntax-grammar -> paragraph 100 -> sentence 100
 * NUMBER: 1
 * DESCRIPTION: parenthesizedAssignableExpression paren assignable
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p99.pos1

fun case1() { var x = 0; (x) += 1 }
