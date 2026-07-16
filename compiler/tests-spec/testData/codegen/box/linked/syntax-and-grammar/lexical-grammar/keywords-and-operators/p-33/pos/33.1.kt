// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 33 -> sentence 33
 * NUMBER: 1
 * DESCRIPTION: DOUBLE_SEMICOLON token in string literal ";;"
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val token = ";;"
    if (!token.all { it == ';' }) return "NOK"
    return "OK"
}
