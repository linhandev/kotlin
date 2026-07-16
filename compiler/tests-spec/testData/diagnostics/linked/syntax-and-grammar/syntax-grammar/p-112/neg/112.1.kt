// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 112 -> sentence 112
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 113 -> sentence 113
 * NUMBER: 1
 * DESCRIPTION: stringLiteral unclosed string
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p112.neg1

fun case1() { val s = "abc }<!SYNTAX!><!>
