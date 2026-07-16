// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 138 -> sentence 138
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 63 -> sentence 63
 * syntax-and-grammar, syntax-grammar -> paragraph 173 -> sentence 173
 * NUMBER: 1
 * DESCRIPTION: callableReference missing identifier after coloncolon
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p138.neg1

fun case1() { val f = :: <!SYNTAX!><!>}
