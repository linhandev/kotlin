// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 65 -> sentence 65
 * NUMBER: 5
 * DESCRIPTION: PACKAGE token as backtick-escaped identifier fun `package`
 */
// TESTCASE NUMBER: 1

fun `package`(): String = "kw-pos-65-5"

fun box(): String {
    val r = `package`()
    when (r) {
        "kw-pos-65-5" -> return "OK"
        else -> return "NOK"
    }
}
