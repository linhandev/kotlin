// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 99 -> sentence 99
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 98 -> sentence 98
 * NUMBER: 1
 * DESCRIPTION: parenthesizedAssignableExpression invalid assign target
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p99.neg1

fun case1() { (<!VARIABLE_EXPECTED!>2<!>) = 3 }
