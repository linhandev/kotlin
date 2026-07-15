// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 120 -> sentence 120
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 121 -> sentence 121
 * syntax-and-grammar, syntax-grammar -> paragraph 119 -> sentence 119
 * NUMBER: 3
 * DESCRIPTION: lambdaParameters trailing comma in parameter list
 */
package syntax.grammar.p120.pos3

// TESTCASE NUMBER: 1
fun box(): String {
    val f: (Int, Int) -> Int = { a, b, -> a + b }
    return if (f(1, 2) == 3) "OK" else "NOK"
}
