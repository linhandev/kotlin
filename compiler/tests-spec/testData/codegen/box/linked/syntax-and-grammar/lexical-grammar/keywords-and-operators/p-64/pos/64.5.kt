// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 64 -> sentence 64
 * NUMBER: 5
 * DESCRIPTION: DELEGATE token as backtick-escaped identifier fun `delegate`
 */
// TESTCASE NUMBER: 1

fun `delegate`(): String = "kw-pos-64-5"

fun box(): String {
    val r = `delegate`()
    return when (r) {
        "kw-pos-64-5" -> "OK"
        else -> "NOK"
    }
}
