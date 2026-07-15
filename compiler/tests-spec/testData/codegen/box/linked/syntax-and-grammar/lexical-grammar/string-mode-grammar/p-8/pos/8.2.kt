// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 8 -> sentence 8
 * NUMBER: 2
 * DESCRIPTION: LineStrExprStart ${a + b} with variables
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val a = 2
    val b = 3
    return if ("${a + b}" == "5") "OK" else "NOK"
}
