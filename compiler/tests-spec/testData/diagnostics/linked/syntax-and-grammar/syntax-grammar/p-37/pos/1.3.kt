// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 37 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: propertyDeclaration multiVariableDeclaration destructuring
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p37.pos3

fun case1(): Int {
    data class Two(val a: Int, val b: Int)
    val pair = Two(1, 2)
    val (first, second) = pair
    return first + second
}
