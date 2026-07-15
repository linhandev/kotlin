// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 84 -> sentence 84
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 85 -> sentence 85
 * syntax-and-grammar, syntax-grammar -> paragraph 143 -> sentence 143
 * syntax-and-grammar, syntax-grammar -> paragraph 51 -> sentence 51
 * NUMBER: 1
 * DESCRIPTION: infixOperation trailing is operator missing type
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p84.neg1

fun case1() { val x = "a" is <!SYNTAX!><!>}
