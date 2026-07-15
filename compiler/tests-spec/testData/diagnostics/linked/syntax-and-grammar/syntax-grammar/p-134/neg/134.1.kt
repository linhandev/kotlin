// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 134 -> sentence 134
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 70 -> sentence 70
 * syntax-and-grammar, syntax-grammar -> paragraph 135 -> sentence 135
 * syntax-and-grammar, syntax-grammar -> paragraph 136 -> sentence 136
 * NUMBER: 1
 * DESCRIPTION: tryExpression missing try block
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p134.neg1

fun case1() { try <!SYNTAX!><!>}<!SYNTAX!><!>
