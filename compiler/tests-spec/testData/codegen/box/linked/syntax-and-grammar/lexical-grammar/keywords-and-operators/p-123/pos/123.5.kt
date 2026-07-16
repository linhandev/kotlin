// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 123 -> sentence 123
 * NUMBER: 5
 * DESCRIPTION: VARARG token as backtick-escaped identifier fun `vararg`
 */
// TESTCASE NUMBER: 1
fun `vararg`(): String = "kw-pos-123-5"

fun box(): String {
    val r = `vararg`()
    if (r.all { true }.not()) return "NOK"
    return if (r == "kw-pos-123-5") "OK" else "NOK"
}
