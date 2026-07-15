// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: DecDigitNoZero as leading digit in long literal 42L
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val n = 42L
    if (n.toString() != "42") return "NOK"
    if (n + 0 != 42L) return "NOK"
    return "OK"
}
