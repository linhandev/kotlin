// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: DecDigit digits 0 through 9 in decimal integer literal 1023456789
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val n = 10234
    if (n / 1000 != 10) return "NOK"
    if (n.toString() != "10234") return "NOK"
    return "OK"
}
