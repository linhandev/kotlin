// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 111 -> sentence 111
 * NUMBER: 4
 * DESCRIPTION: literalConstant hex character and long literals
 */
package syntax.grammar.p111.pos4

// TESTCASE NUMBER: 1
fun box(): String {
    val hex = 0xFF
    val ch = 'a'
    val lng = 1L
    return if (hex == 255 && ch == 'a' && lng == 1L) "OK" else "NOK"
}
