// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 107 -> sentence 107
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 173 -> sentence 173
 * syntax-and-grammar, syntax-grammar -> paragraph 79 -> sentence 79
 * NUMBER: 1
 * DESCRIPTION: valueArgument missing expression after named arg
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p107.neg1

fun f(x: Int): Int = x

fun case1() { f(x =<!SYNTAX!><!> }
