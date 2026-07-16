// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 123 -> sentence 123
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 119 -> sentence 119
 * NUMBER: 1
 * DESCRIPTION: functionLiteral trailing token after complete lambda
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p123.neg1

fun case1() { val g = { 2 }<!SYNTAX!><!>
