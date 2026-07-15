// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 2 -> sentence 2
 * NUMBER: 5
 * DESCRIPTION: DecDigit trailing zero in integer literal 10
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val n = 10
    if (n % 10 != 0) return "NOK"
    if (n.toString() != "10") return "NOK"
    return "OK"
}
