// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: SEMICOLON token used to separate statements on one line val a = 1; val b = 2
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val a = 1; val b = 2
    return if (a + b == 3) "OK" else "NOK"
}
