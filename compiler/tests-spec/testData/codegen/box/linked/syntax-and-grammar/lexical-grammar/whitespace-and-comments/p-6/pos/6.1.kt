// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: SPACE (U+0020) as whitespace between tokens
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val x = 1
    return if (x == 1) "OK" else "NOK"
}
