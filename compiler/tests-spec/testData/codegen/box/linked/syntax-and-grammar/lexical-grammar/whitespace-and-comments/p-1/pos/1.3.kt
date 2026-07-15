// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: LF character in string literal with \n escape; escape produces line break
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val s = "a\nb"
    if (s.length != 3) return "NOK"
    return if (s[1] == '\n') "OK" else "NOK"
}
