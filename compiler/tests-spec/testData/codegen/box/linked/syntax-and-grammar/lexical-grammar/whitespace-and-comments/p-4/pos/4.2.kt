// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 4 -> sentence 4
 * NUMBER: 2
 * DESCRIPTION: Nested block comments
 */

// TESTCASE NUMBER: 1
fun box(): String {
    /* Outer comment /* Inner comment */ outer continues */
    val x = 2 + 1
    return if (x == 3) "OK" else "NOK"
}