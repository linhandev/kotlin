// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 6 -> sentence 6
 * NUMBER: 2
 * DESCRIPTION: TAB (U+0009) as whitespace between tokens; TAB-separated declaration binds correct value
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val	x = 62
    return if (x + 0 == 62) "OK" else "NOK"
}
