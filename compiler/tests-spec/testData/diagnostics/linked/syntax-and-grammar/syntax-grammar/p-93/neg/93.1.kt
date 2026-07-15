// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 93 -> sentence 93
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 147 -> sentence 147
 * syntax-and-grammar, syntax-grammar -> paragraph 68 -> sentence 68
 * syntax-and-grammar, syntax-grammar -> paragraph 169 -> sentence 169
 * NUMBER: 1
 * DESCRIPTION: unaryPrefix invalid double prefix operator missing operand
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p93.neg1

fun case1() { val x = <!DEBUG_INFO_MISSING_UNRESOLVED!>++<!><!SYNTAX!><!> }
