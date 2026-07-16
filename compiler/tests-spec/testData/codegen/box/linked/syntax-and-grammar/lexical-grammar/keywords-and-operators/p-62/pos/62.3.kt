// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 62 -> sentence 62
 * NUMBER: 3
 * DESCRIPTION: PARAM token in bracket use-site @param:[Suppress] on constructor parameter
 */
// TESTCASE NUMBER: 1

class BracketParam62(@param:[Suppress("UNUSED_PARAMETER")] val code: String)

fun box(): String {
    val expected = "param-62"
    if (BracketParam62(expected).code != expected) return "NOK"
    return "OK"
}
