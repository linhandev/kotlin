// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 126 -> sentence 126
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 51 -> sentence 51
 * syntax-and-grammar, syntax-grammar -> paragraph 173 -> sentence 173
 * NUMBER: 1
 * DESCRIPTION: superExpression super outside class
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p126.neg1

fun case1() { <!SUPER_IS_NOT_AN_EXPRESSION!>super<!> }
