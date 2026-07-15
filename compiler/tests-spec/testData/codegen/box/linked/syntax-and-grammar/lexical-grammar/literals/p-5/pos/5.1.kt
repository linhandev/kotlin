// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: DoubleExponent with lowercase e and DecDigits in 1.0e10
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val n = 1.0e10
    if (n.toString() != "1.0E10") return "NOK"
    if (n + 0 != 1.0e10) return "NOK"
    return "OK"
}
