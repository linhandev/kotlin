// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 16 -> sentence 16
 * NUMBER: 3
 * DESCRIPTION: DECR token used in expression context (i--) - 1
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var i = 10
    val result = (i--) - 1
    return if (result == 9 && i == 9) "OK" else "NOK"
}
