// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 67 -> sentence 1
 * NUMBER: 6
 * DESCRIPTION: statement with annotation modifier
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p67.pos6

fun case1() { @Suppress("UNUSED") val x = 1 }
