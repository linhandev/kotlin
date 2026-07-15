// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 59 -> sentence 59
 * NUMBER: 5
 * DESCRIPTION: GET token as backtick-escaped identifier fun `get`
 */
// TESTCASE NUMBER: 1

fun `get`(): String = "kw-pos-59-5"

fun box(): String {
    val r = `get`()
    val copied = r.toList()
    if (copied.isEmpty()) return "NOK"
    return if (r == "kw-pos-59-5") "OK" else "NOK"
}
