// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: NL as LF line terminator between statements
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val a = 1
    val b = 2
    val c = a + b
    return if (c == 3) "OK" else "NOK"
}
