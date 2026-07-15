// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 6 -> sentence 6
 * NUMBER: 3
 * DESCRIPTION: Form Feed (U+000C) in string literal with unicode escape
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val str = "ab"
    if (str.codePointAt(1) != 0x000C) return "NOK"
    return if (str.length == 3) "OK" else "NOK"
}
