// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: Simple block comment
 */

// TESTCASE NUMBER: 1
fun box(): String {
    /* This is a block comment */
    val x = 4 - 1
    return if (x == 3) "OK" else "NOK"
}