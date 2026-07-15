// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: LF character (U+000A) in string literal with unicode escape \u000A; escape produces line break
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val str = "x\u000Ay"
    if (str.length != 3) return "NOK"
    if (str[1].code != 0x0A) return "NOK"
    val lines = str.lines()
    return if (lines.size == 2 && lines[0] == "x" && lines[1] == "y") "OK" else "NOK"
}
