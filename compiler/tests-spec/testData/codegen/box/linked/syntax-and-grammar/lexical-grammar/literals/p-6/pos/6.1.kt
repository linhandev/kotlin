// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: RealLiteral as FloatLiteral 2.5f
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val n = 2.5f
    if (n.isFinite().not()) return "NOK"
    return if (n == 2.5f) "OK" else "NOK"
}
