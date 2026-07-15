// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 4 -> sentence 4
 * NUMBER: 2
 * DESCRIPTION: LPAREN token used for expression grouping (1 + 2) * 3
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val result = (1 + 2) * 3
    return if (result == 9) "OK" else "NOK"
}
