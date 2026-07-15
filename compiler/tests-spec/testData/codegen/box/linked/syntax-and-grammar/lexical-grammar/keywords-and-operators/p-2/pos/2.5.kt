// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 2 -> sentence 2
 * NUMBER: 5
 * DESCRIPTION: DOT token used in range expression (0..10)
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val range = 0..10
    return if (range.first == 0 && range.last == 10) "OK" else "NOK"
}
