// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 11 -> sentence 11
 * NUMBER: 5
 * DESCRIPTION: CharacterLiteral and UnsignedLiteral 42u with HexLiteral 0XFFu
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val ch: Char = 'A'
    val unsigned = 0XFFu
    return if (ch == 'A' && unsigned == 255u) "OK" else "NOK"
}
