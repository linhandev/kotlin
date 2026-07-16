// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 155 -> sentence 155
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 156 -> sentence 156
 * NUMBER: 1
 * DESCRIPTION: typeModifiers suspend function type modifier
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p155.pos1

fun case1(block: suspend () -> Unit) {}
