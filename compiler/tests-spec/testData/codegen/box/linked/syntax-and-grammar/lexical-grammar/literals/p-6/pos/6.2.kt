// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 6 -> sentence 6
 * NUMBER: 2
 * DESCRIPTION: RealLiteral as DoubleLiteral with exponent form 1e10
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val n = 1e10
    if (n.toString() != "1.0E10") return "NOK"
    if (n + 0 != 1e10) return "NOK"
    return "OK"
}
