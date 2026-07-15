// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: DecDigitNoZero as leading digit in double literal 1.5
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val n = 1.5
    if (n.toString() != "1.5") return "NOK"
    if ((n * 2).toInt() != 3) return "NOK"
    return "OK"
}
