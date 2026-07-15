// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: Line comment with code on next line
 */

// TESTCASE NUMBER: 1
fun box(): String {
    // comment
    val x = 42
    if (x != 42) return "NOK"
    return "OK"
}