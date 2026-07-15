// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 121 -> sentence 121
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 35 -> sentence 35
 * syntax-and-grammar, syntax-grammar -> paragraph 120 -> sentence 120
 * NUMBER: 1
 * DESCRIPTION: lambdaParameter single lambda param
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p121.pos1

fun case1() { val f: (Int) -> Unit = { n -> n + 1 } }
