// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 60 -> sentence 60
 * NUMBER: 5
 * DESCRIPTION: SET token as backtick-escaped identifier fun `set`
 */
// TESTCASE NUMBER: 1

fun `set`(value: String): String = value

fun box(): String {
    val expected = "fn-set-60"
    if (`set`(expected) != expected) return "NOK"
    return "OK"
}
