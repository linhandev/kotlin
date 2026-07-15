// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 107 -> sentence 107
 * NUMBER: 5
 * DESCRIPTION: SEALED token as backtick-escaped identifier fun `sealed`
 */
// TESTCASE NUMBER: 1
fun `sealed`(): String = "kw-pos-107-5"

fun box(): String {
    val r = `sealed`().also { }
    return if (r == "kw-pos-107-5") "OK" else "NOK"
}
