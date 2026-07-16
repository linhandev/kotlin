// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 121 -> sentence 121
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 36 -> sentence 36
 * syntax-and-grammar, syntax-grammar -> paragraph 120 -> sentence 120
 * NUMBER: 3
 * DESCRIPTION: lambdaParameter multiVariableDeclaration destructuring
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p121.pos3

fun case1() { val f: (Pair<Int, Int>) -> Int = { (a, b) -> a + b }; check(f(1 to 2) == 3) }
