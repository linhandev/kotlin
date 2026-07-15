// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: DecDigits single DecDigit alternative in integer literal 7
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val n = 7
    if (n.toString().length != 1) return "NOK"
    return if (n == 7) "OK" else "NOK"
}
