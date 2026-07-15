// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 88 -> sentence 88
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 89 -> sentence 89
 * NUMBER: 1
 * DESCRIPTION: rangeExpression missing end additiveExpression
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p88.neg1

fun case1() { val x = 1..<!SYNTAX!><!> }
