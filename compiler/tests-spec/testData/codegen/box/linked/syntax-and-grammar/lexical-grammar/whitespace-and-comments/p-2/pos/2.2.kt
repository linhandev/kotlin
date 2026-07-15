// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: CR character (U+000D) as Char literal with unicode escape
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val c: Char = '\u000D'
    return if (c.code == 13) "OK" else "NOK"
}
