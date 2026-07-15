// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 96 -> sentence 96
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 94 -> sentence 94
 * syntax-and-grammar, syntax-grammar -> paragraph 100 -> sentence 100
 * syntax-and-grammar, syntax-grammar -> paragraph 101 -> sentence 101
 * NUMBER: 2
 * DESCRIPTION: directlyAssignableExpression array index assign
 */
package syntax.grammar.p96.pos2

// TESTCASE NUMBER: 1
fun box(): String {
    val a = intArrayOf(0)
    a[0] = 1
    return if (a[0] == 1) "OK" else "NOK"
}
