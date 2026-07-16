// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 115 -> sentence 115
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 113 -> sentence 113
 * NUMBER: 1
 * DESCRIPTION: lineStringContent LineStrText plain text
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p115.pos1

fun case1() { val s = "plain text"; check(s == "plain text") }
