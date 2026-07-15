// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 111 -> sentence 111
 * NUMBER: 5
 * DESCRIPTION: literalConstant real binary and unsigned literals
 */
package syntax.grammar.p111.pos5

// TESTCASE NUMBER: 1
fun box(): String {
    val real = 1.5
    val bin = 0b101
    val unsigned = 1u
    return if (real == 1.5 && bin == 5 && unsigned == 1u) "OK" else "NOK"
}
