// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 41 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: parametersWithOptionalType single parameter with type
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p41.pos3

fun case1(): Int {
    val f = fun (value: Int): Int = value
    return f(1)
}
