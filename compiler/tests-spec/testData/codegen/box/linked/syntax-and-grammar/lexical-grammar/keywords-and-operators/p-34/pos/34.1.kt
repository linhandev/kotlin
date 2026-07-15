// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 34 -> sentence 34
 * NUMBER: 1
 * DESCRIPTION: HASH token in string literal "#"
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val token = "#"
    if (token.single() != '#') return "NOK"
    return "OK"
}
