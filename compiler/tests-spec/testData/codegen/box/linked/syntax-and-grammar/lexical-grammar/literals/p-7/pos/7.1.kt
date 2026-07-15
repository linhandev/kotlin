// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: FloatLiteral as DoubleLiteral with f suffix 1.5f
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val n = 1.5f
    if (n.toString().endsWith("1.5")) return "OK"
    return "NOK"
}
