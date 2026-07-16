// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 167 -> sentence 167
 * NUMBER: 1
 * DESCRIPTION: reificationModifier reified keyword
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p167.pos1

inline fun <reified T> case1(): Boolean = T::class == Int::class
