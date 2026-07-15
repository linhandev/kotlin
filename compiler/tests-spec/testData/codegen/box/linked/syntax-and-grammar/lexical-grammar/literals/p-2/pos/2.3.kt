// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 2 -> sentence 2
 * NUMBER: 3
 * DESCRIPTION: DecDigit zero in double literal 0.5
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val n = 0.5
    if (n + 0.5 != 1.0) return "NOK"
    return if (n.toString() == "0.5") "OK" else "NOK"
}
