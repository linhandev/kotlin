// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 123 -> sentence 123
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 119 -> sentence 119
 * NUMBER: 1
 * DESCRIPTION: functionLiteral lambda literal
 */
package syntax.grammar.p123.pos1

// TESTCASE NUMBER: 1
fun box(): String {
    val f: (Int) -> Int = { n -> n * 2 }
    return if (f(3) == 6) "OK" else "NOK"
}
