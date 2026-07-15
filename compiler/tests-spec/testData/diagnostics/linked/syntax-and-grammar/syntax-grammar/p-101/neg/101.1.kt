// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 101 -> sentence 101
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 79 -> sentence 79
 * NUMBER: 1
 * DESCRIPTION: indexingSuffix missing closing bracket
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p101.neg1

fun case1() { val a = intArrayOf(1); val x = a[0<!SYNTAX!><!> }
