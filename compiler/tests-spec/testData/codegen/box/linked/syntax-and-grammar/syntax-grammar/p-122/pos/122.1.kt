// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 122 -> sentence 122
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 41 -> sentence 41
 * syntax-and-grammar, syntax-grammar -> paragraph 34 -> sentence 34
 * NUMBER: 1
 * DESCRIPTION: anonymousFunction fun expression
 */
package syntax.grammar.p122.pos1

// TESTCASE NUMBER: 1
fun box(): String {
    val f = fun(x: Int): Int = x + 1
    return if (f(2) == 3) "OK" else "NOK"
}
