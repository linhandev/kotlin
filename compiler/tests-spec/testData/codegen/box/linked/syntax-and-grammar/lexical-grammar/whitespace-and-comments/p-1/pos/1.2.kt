// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: LF character (U+000A) as Char literal with unicode escape
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val c: Char = '\u000A'
    return if (c == '\n') "OK" else "NOK"
}
