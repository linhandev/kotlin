// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: DoubleLiteral with DecDigits dot DecDigits and DoubleExponent 1.5e10
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val n = 1.5e10
    if (n.toString() != "1.5E10") return "NOK"
    if (n + 0 != 1.5e10) return "NOK"
    return "OK"
}
