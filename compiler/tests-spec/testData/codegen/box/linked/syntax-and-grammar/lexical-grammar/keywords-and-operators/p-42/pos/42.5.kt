// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 42 -> sentence 42
 * NUMBER: 5
 * DESCRIPTION: RANGLE token in string literal ">"
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val token = ">"
    if (token.get(0) != '>') return "NOK"
    return if (token.length == 1) "OK" else "NOK"
}
