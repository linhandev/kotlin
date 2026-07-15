// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 50 -> sentence 50
 * NUMBER: 1
 * DESCRIPTION: SINGLE_QUOTE token in character literal 'a'
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val c = 'a'
    if (!c.isLowerCase()) return "NOK"
    return if (c == 'a') "OK" else "NOK"
}
