// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 161 -> sentence 161
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 162 -> sentence 162
 * NUMBER: 1
 * DESCRIPTION: typeParameterModifiers reified type parameter modifier
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p161.pos1

inline fun <reified T> case1(): T = TODO()
