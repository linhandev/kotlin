// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 50 -> sentence 50
 * NUMBER: 4
 * DESCRIPTION: SINGLE_QUOTE token as apostrophe inside string literal
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val s = "quote='"
    return if (s.length == 7 && s[6] == '\'') "OK" else "NOK"
}
