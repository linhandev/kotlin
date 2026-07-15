// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: MULT token used in multiplication a * b
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val result = 6 * 7
    return if (result == 42) "OK" else "NOK"
}
