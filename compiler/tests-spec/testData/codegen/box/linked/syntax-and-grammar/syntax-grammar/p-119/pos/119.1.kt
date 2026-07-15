// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 119 -> sentence 119
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 66 -> sentence 66
 * NUMBER: 1
 * DESCRIPTION: lambdaLiteral anonymous function value
 */
package syntax.grammar.p119.pos1

// TESTCASE NUMBER: 1
fun box(): String {
    val f: () -> Int = { 42 }
    return if (f() == 42) "OK" else "NOK"
}
