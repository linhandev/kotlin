// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 24 -> sentence 24
 * NUMBER: 5
 * DESCRIPTION: ADD_ASSIGNMENT token used with compound right-hand expression x += a + b
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var x = 1
    val a = 2
    val b = 3
    x += a + b
    return if (x == 6) "OK" else "NOK"
}
