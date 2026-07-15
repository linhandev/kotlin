// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 83 -> sentence 83
 * NUMBER: 5
 * DESCRIPTION: ELSE token as backtick-escaped identifier fun `else`
 */
// TESTCASE NUMBER: 1

fun `else`(): String = "kw-pos-83-5"

fun box(): String {
    val r = `else`()
    return if (r.contentEquals("kw-pos-83-5")) "OK" else "NOK"
}
