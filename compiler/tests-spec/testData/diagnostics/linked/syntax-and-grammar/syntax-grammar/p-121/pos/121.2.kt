// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 121 -> sentence 121
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 35 -> sentence 35
 * syntax-and-grammar, syntax-grammar -> paragraph 51 -> sentence 51
 * NUMBER: 2
 * DESCRIPTION: lambdaParameter variableDeclaration with explicit type
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p121.pos2

fun case1() { val f: (Int) -> Int = { n: Int -> n + 1 } }
