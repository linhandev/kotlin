// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 61 -> sentence 61
 * NUMBER: 5
 * DESCRIPTION: RECEIVER token as backtick-escaped identifier fun `receiver`
 */
// TESTCASE NUMBER: 1

fun `receiver`(): String = "kw-pos-61-5"

fun box(): String {
    val r = `receiver`()
    if (r.count { true } != 11) return "NOK"
    return if (r == "kw-pos-61-5") "OK" else "NOK"
}
