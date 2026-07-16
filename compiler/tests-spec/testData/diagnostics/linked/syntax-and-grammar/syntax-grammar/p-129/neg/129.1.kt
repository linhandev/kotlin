// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 129 -> sentence 129
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 128 -> sentence 128
 * syntax-and-grammar, syntax-grammar -> paragraph 130 -> sentence 130
 * NUMBER: 1
 * DESCRIPTION: whenExpression missing when body
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p129.neg1

fun case1() { when (1)<!SYNTAX!><!>
