// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 5 -> sentence 5
 * NUMBER: 2
 * DESCRIPTION: DoubleExponent with uppercase E and plus sign in 2.5E+20
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val n = 2.5E+20
    if (n.toString() != "2.5E20") return "NOK"
    if (n + 0 != 2.5E+20) return "NOK"
    return "OK"
}
