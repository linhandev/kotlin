// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 8 -> sentence 8
 * NUMBER: 4
 * DESCRIPTION: LineStrExprStart nested template in expression
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val part = "X"
    return if ("${"Y$part"}" == "YX") "OK" else "NOK"
}
