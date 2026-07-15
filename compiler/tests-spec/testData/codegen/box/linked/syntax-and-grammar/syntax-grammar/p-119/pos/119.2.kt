// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 119 -> sentence 119
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 120 -> sentence 120
 * NUMBER: 2
 * DESCRIPTION: lambdaLiteral with explicit lambda parameters
 */
package syntax.grammar.p119.pos2

// TESTCASE NUMBER: 1
fun box(): String {
    val f: (Int) -> Int = { x -> x + 1 }
    return if (f(1) == 2) "OK" else "NOK"
}
