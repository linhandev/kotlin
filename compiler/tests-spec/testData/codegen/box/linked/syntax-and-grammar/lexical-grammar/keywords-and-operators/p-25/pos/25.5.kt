// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 25 -> sentence 25
 * NUMBER: 5
 * DESCRIPTION: SUB_ASSIGNMENT token used with compound right-hand expression x -= a - b
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var x = 10
    val a = 3
    val b = 1
    x -= a - b
    return if (x == 8) "OK" else "NOK"
}
