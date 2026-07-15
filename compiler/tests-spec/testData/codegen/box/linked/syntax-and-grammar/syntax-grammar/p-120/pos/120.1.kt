// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 120 -> sentence 120
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 121 -> sentence 121
 * syntax-and-grammar, syntax-grammar -> paragraph 119 -> sentence 119
 * NUMBER: 1
 * DESCRIPTION: lambdaParameters single explicit parameter
 */
package syntax.grammar.p120.pos1

// TESTCASE NUMBER: 1
fun box(): String {
    val f: (Int) -> Int = { x: Int -> x + 1 }
    return if (f(1) == 2) "OK" else "NOK"
}
