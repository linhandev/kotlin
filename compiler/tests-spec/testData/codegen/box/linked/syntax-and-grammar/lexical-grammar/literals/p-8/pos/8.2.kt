// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 8 -> sentence 8
 * NUMBER: 2
 * DESCRIPTION: DoubleLiteral DecDigits DoubleExponent form without dot 5e10
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val n = 5e10
    if (n.toString() != "5.0E10") return "NOK"
    if (n + 0 != 5e10) return "NOK"
    return "OK"
}
