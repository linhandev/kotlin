// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: COLON token used in variable type annotation val x: Int
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val x: Int = 42
    return if (x == 42) "OK" else "NOK"
}
