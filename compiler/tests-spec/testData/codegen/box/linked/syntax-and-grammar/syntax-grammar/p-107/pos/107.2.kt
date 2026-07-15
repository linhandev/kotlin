// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 107 -> sentence 107
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 79 -> sentence 79
 * NUMBER: 2
 * DESCRIPTION: valueArgument spread star operator
 */
// TESTCASE NUMBER: 1
package syntax.grammar.p107.pos2

fun sum(vararg xs: Int): Int = xs.sum()

fun box(): String {
    val arr = intArrayOf(1, 2)
    return if (sum(*arr) == 3) "OK" else "NOK"
}
