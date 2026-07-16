// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 145 -> sentence 145
 * NUMBER: 2
 * DESCRIPTION: multiplicativeOperator divide and remainder operators
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p145.pos2

fun case1() {
    val a = 6 / 2
    val b = 7 % 3
}
