// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: IntegerLiteral DecDigitNoZero leading digit 987654321
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val n = 987654321
    if (n.toString() != "987654321") return "NOK"
    if (n - 1 != 987654320) return "NOK"
    return "OK"
}
