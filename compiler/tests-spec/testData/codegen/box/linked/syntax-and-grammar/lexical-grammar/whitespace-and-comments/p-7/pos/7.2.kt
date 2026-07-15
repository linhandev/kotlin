// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 7 -> sentence 7
 * NUMBER: 2
 * DESCRIPTION: Multiple consecutive LF (\n) in string literal, split by lines()
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val lines = "a\n\nb".lines()
    return if (lines.size == 3) "OK" else "NOK"
}
