// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 113 -> sentence 113
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 115 -> sentence 115
 * NUMBER: 1
 * DESCRIPTION: lineStringLiteral unclosed line string
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p113.neg1

fun case1() { val s = "x }<!SYNTAX!><!>
