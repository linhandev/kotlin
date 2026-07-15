// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 2 -> sentence 2
 * NUMBER: 4
 * DESCRIPTION: DecDigit zero in long literal 0L
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val n = 0L
    if (n.toString() != "0") return "NOK"
    if (n + 0 != 0L) return "NOK"
    return "OK"
}
