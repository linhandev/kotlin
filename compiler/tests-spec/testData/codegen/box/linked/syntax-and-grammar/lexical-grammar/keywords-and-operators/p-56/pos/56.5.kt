// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 56 -> sentence 56
 * NUMBER: 5
 * DESCRIPTION: FILE token as backtick-escaped identifier fun `file`
 */
// TESTCASE NUMBER: 1

fun `file`(): String = "kw-pos-56-5"

fun box(): String {
    val r = `file`()
    if (r.startsWith("kw-pos-56-5".substring(0, 2)).not()) return "NOK"
    return if (r == "kw-pos-56-5") "OK" else "NOK"
}
