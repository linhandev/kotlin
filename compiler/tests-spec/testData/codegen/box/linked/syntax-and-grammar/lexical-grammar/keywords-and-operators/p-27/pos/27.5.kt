// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 27 -> sentence 27
 * NUMBER: 5
 * DESCRIPTION: DIV_ASSIGNMENT token used with compound right-hand expression x /= a / b
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var x = 100
    val a = 20
    val b = 5
    x /= a / b
    return if (x == 25) "OK" else "NOK"
}
