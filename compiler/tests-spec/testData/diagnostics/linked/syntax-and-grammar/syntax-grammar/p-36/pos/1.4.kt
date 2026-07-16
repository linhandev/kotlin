// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 36 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: multiVariableDeclaration NL between declarations
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p36.pos4

fun case1(): Int {
    data class Two(val a: Int, val b: Int)
    val (
        a,
        b
    ) = Two(1, 2)
    return a + b
}
