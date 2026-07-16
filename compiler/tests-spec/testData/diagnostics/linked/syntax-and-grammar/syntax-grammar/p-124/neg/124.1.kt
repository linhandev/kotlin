// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 124 -> sentence 124
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: objectLiteral trailing token after complete object
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p124.neg1

fun case1() { val o = object { fun v() = 1 }<!SYNTAX!><!>
