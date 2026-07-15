// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 7 -> sentence 7
 * NUMBER: 3
 * DESCRIPTION: NL as CR followed by LF in string via \r\n
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val s = "a\r\nb"
    return if (s.contains("\r\n")) "OK" else "NOK"
}
