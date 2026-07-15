// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 123 -> sentence 123
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 122 -> sentence 122
 * NUMBER: 2
 * DESCRIPTION: functionLiteral anonymous function
 */
package syntax.grammar.p123.pos2

// TESTCASE NUMBER: 1
fun box(): String {
    val f = (fun(x: Int): Int = x + 1)
    return if (f(3) == 4) "OK" else "NOK"
}
