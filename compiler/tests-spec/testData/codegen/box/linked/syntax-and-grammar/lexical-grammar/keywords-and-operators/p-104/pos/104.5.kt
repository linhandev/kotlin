// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 104 -> sentence 104
 * NUMBER: 5
 * DESCRIPTION: PROTECTED token as backtick-escaped identifier fun `protected`
 */
// TESTCASE NUMBER: 1
fun `protected`(): String = "kw-pos-104-5"

fun box(): String {
    val r = `protected`()
    if (r.map { it }.size != 12) return "NOK"
    return if (r == "kw-pos-104-5") "OK" else "NOK"
}
