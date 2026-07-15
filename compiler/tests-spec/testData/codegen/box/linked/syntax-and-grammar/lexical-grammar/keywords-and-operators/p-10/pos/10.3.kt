// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 10 -> sentence 10
 * NUMBER: 3
 * DESCRIPTION: MULT token used in compound expression a * b + c (operator precedence)
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val result = 2 * 3 + 4 * 5
    return if (result == 26) "OK" else "NOK"
}
