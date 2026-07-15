// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 81 -> sentence 81
 * NUMBER: 5
 * DESCRIPTION: WHERE token as backtick-escaped identifier fun `where`
 */
// TESTCASE NUMBER: 1

fun `where`(): String = "kw-pos-81-5"

fun box(): String {
    return if (`where`().let { if (it == "kw-pos-81-5") "OK" else "NOK" } == "OK") "OK" else "NOK"
}
