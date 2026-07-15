// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 147 -> sentence 147
 * NUMBER: 1
 * DESCRIPTION: prefixUnaryOperator unary minus token
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p147.pos1

fun case1() { val n = -1 }
