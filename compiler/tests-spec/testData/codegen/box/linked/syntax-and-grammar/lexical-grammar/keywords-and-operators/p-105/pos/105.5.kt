// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 105 -> sentence 105
 * NUMBER: 5
 * DESCRIPTION: INTERNAL token as backtick-escaped identifier fun `internal`
 */
// TESTCASE NUMBER: 1
fun `internal`(): String = "kw-pos-105-5"

fun box(): String {
    val r = `internal`()
    val ok = r.contentEquals("kw-pos-105-5")
    return if (ok) "OK" else "NOK"
}
