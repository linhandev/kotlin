// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: EXCL_WS token with space as Hidden between ! and operand (! x)
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val flag = false
    val result = ! flag
    return if (result) "OK" else "NOK"
}
