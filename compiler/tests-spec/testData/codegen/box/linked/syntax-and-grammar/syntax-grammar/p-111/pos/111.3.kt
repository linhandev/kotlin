// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 111 -> sentence 111
 * NUMBER: 3
 * DESCRIPTION: literalConstant null literal
 */
package syntax.grammar.p111.pos3

// TESTCASE NUMBER: 1
fun box(): String {
    val x: String? = null
    return if (x == null) "OK" else "NOK"
}
