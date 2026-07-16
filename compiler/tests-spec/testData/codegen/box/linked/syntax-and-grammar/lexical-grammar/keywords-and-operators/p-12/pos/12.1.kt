// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: DIV token used in division a / b
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val result = 42 / 6
    return if (result == 7) "OK" else "NOK"
}
