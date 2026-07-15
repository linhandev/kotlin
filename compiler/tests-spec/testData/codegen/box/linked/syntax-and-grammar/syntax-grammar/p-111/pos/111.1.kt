// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 111 -> sentence 111
 * NUMBER: 1
 * DESCRIPTION: literalConstant integer literal
 */
package syntax.grammar.p111.pos1

// TESTCASE NUMBER: 1
fun box(): String {
    val n = 100
    if (n.toString() != "100") return "NOK"
    if (n / 10 != 10) return "NOK"
    return "OK"
}
