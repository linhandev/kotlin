// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 127 -> sentence 127
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 78 -> sentence 78
 * syntax-and-grammar, syntax-grammar -> paragraph 69 -> sentence 69
 * NUMBER: 1
 * DESCRIPTION: ifExpression missing condition
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p127.neg1

fun case1() { val x = if (<!SYNTAX!><!>) 1 else 2 }
