// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 33 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: functionDeclaration simple with expression body
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p33.pos1

fun exprReturn(n: Int): Int = n + 1
