// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 4 -> sentence 4
 * NUMBER: 4
 * DESCRIPTION: DecDigits ending with DecDigit in long literal 999L
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val n = 999L
    if (n.toString() != "999") return "NOK"
    if (n + 0 != 999L) return "NOK"
    return "OK"
}
