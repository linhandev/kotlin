// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 4 -> sentence 4
 * NUMBER: 2
 * DESCRIPTION: DecDigits multi-digit sequence in integer literal 12345
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val n = 12345
    if (n / 10000 != 1) return "NOK"
    return if (n == 12345) "OK" else "NOK"
}
