// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 15 -> sentence 15
 * NUMBER: 3
 * DESCRIPTION: INCR token used in expression context (i++) + 1
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var i = 10
    val result = (i++) + 1
    return if (result == 11 && i == 11) "OK" else "NOK"
}
