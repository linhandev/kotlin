// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: EXCL_NO_WS token used in unary NOT operation !x without Hidden
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val flag = false
    val result = !flag
    return if (result) "OK" else "NOK"
}
