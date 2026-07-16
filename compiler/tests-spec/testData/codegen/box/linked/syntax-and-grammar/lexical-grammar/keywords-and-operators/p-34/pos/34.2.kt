// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 34 -> sentence 34
 * NUMBER: 2
 * DESCRIPTION: HASH token in character literal '#'
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val ch = '#'
    if (ch.code != 35) return "NOK"
    return "OK"
}
