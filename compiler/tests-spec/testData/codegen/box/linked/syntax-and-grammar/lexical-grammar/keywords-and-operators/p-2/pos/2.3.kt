// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 2 -> sentence 2
 * NUMBER: 3
 * DESCRIPTION: DOT token used in floating-point literal (1.0, 3.14)
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val a = 1.0
    val b = 3.14
    val sum = a + b
    return if (sum > 4.13 && sum < 4.15) "OK" else "NOK"
}
