// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 5 -> sentence 5
 * NUMBER: 2
 * DESCRIPTION: Multiple line comments
 */

// TESTCASE NUMBER: 1
fun box(): String {
    // comment one
    /* comment two */
    val x = 1
    val y = 2
    return if (x + y == 3) "OK" else "NOK"
}
