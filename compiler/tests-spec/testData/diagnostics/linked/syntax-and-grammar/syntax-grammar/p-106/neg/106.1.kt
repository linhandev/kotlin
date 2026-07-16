// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 106 -> sentence 106
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 107 -> sentence 107
 * NUMBER: 1
 * DESCRIPTION: valueArguments missing closing paren
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p106.neg1

fun f(a: Int): Int = a

fun case1() { val x = f(1<!SYNTAX!><!> }
