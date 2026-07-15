// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: SUB token used in subtraction a - b
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val result = 20 - 7
    return if (result == 13) "OK" else "NOK"
}
