// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 28 -> sentence 28
 * NUMBER: 5
 * DESCRIPTION: MOD_ASSIGNMENT token used with compound right-hand expression x %= a % b
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var x = 100
    val a = 17
    val b = 5
    x %= a % b
    return if (x == 0) "OK" else "NOK"
}
