// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 115 -> sentence 115
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 113 -> sentence 113
 * NUMBER: 3
 * DESCRIPTION: lineStringContent LineStrRef string template reference
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p115.pos3

fun case1() { val n = 1; val s = "$n"; check(s == "1") }
