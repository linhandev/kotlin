// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 87 -> sentence 87
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 88 -> sentence 88
 * syntax-and-grammar, syntax-grammar -> paragraph 174 -> sentence 174
 * NUMBER: 1
 * DESCRIPTION: infixFunctionCall trailing infix missing right rangeExpression
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p87.neg1

fun case1() { val x = 1 shl<!SYNTAX!><!> }
