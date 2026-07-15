// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 1 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: Multiple LF characters in string
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val str = "line1\u000Aline2\u000Aline3"
    val lines = str.lines()
    return if (lines.size == 3 && lines[0] == "line1" && lines[1] == "line2" && lines[2] == "line3") "OK" else "NOK"
}