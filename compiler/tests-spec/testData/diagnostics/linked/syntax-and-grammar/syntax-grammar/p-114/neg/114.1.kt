// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 114 -> sentence 114
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 117 -> sentence 117
 * NUMBER: 1
 * DESCRIPTION: multiLineStringLiteral unclosed triple quote
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p114.neg1

fun case1() { val s = """x }<!SYNTAX!><!>