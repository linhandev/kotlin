// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 120 -> sentence 120
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 121 -> sentence 121
 * syntax-and-grammar, syntax-grammar -> paragraph 119 -> sentence 119
 * NUMBER: 1
 * DESCRIPTION: lambdaParameters invalid lambda parameter syntax
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p120.neg1

fun case1() { val f = { <!SYNTAX!><!>: Int -> 1 } }
