// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 144 -> sentence 144
 * NUMBER: 1
 * DESCRIPTION: additiveOperator plus in expression
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p144.pos1

fun case1() { val n = 1 + 2 }
