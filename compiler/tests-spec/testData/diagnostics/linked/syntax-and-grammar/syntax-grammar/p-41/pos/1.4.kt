// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 41 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: parametersWithOptionalType multiple parameters
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p41.pos4

fun case1(): Int {
    val f = fun (a: Int, b: String): Int = a + b.length
    return f(1, "x")
}
