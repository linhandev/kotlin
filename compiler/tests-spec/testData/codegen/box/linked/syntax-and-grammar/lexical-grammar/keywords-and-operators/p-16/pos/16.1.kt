// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: DECR token used in postfix decrement i--
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var i = 6
    val result = i--
    return if (result == 6 && i == 5) "OK" else "NOK"
}
