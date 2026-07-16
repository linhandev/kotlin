// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 41 -> sentence 41
 * NUMBER: 5
 * DESCRIPTION: LANGLE token in string literal "<"
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val token = "<"
    if (token.compareTo("<") != 0) return "NOK"
    return "OK"
}
