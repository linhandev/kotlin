// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 91 -> sentence 91
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 92 -> sentence 92
 * syntax-and-grammar, syntax-grammar -> paragraph 146 -> sentence 146
 * syntax-and-grammar, syntax-grammar -> paragraph 51 -> sentence 51
 * NUMBER: 1
 * DESCRIPTION: asExpression safe cast operator
 */
package syntax.grammar.p91.pos1

// TESTCASE NUMBER: 1
fun box(): String {
    val x: Any = "s"
    return if ((x as? String) == "s") "OK" else "NOK"
}
