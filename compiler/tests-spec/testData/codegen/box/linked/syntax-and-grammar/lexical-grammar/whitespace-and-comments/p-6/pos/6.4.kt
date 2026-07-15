// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 6 -> sentence 6
 * NUMBER: 4
 * DESCRIPTION: Form Feed (U+000C) as whitespace between tokens in source; FF-separated declaration binds correct value
 */

// TESTCASE NUMBER: 1
fun box(): String {
    valx = 64
    return if (x + 0 == 64) "OK" else "NOK"
}
