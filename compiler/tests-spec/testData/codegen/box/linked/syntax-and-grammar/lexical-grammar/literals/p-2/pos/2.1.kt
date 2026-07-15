// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: DecDigit zero as single-digit integer literal 0
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val n = 0
    if (n.toString() != "0") return "NOK"
    if (n + 0 != 0) return "NOK"
    return "OK"
}
