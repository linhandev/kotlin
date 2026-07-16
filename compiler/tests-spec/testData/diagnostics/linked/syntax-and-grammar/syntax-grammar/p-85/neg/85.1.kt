// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 85 -> sentence 85
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 87 -> sentence 87
 * syntax-and-grammar, syntax-grammar -> paragraph 86 -> sentence 86
 * NUMBER: 1
 * DESCRIPTION: elvisExpression trailing elvis operator missing right operand
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p85.neg1

fun case1() { val x = 1 ?:<!SYNTAX!><!> }
