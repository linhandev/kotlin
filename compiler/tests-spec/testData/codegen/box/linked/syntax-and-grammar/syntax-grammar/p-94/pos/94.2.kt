// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 94 -> sentence 94
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 108 -> sentence 108
 * syntax-and-grammar, syntax-grammar -> paragraph 95 -> sentence 95
 * syntax-and-grammar, syntax-grammar -> paragraph 101 -> sentence 101
 * NUMBER: 2
 * DESCRIPTION: postfixUnaryExpression postfix increment and indexing suffix
 */
package syntax.grammar.p94.pos2

// TESTCASE NUMBER: 1
fun box(): String {
    var i = 0
    i++
    val a = intArrayOf(1)
    return if (a[0] == 1 && i == 1) "OK" else "NOK"
}
