// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: INCR token used in postfix increment i++
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var i = 5
    val result = i++
    return if (result == 5 && i == 6) "OK" else "NOK"
}
