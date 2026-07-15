// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 122 -> sentence 122
 * NUMBER: 5
 * DESCRIPTION: LATEINIT token as backtick-escaped identifier fun `lateinit`
 */
// TESTCASE NUMBER: 1
fun `lateinit`(): String = "kw-pos-122-5"

fun box(): String {
    val r = `lateinit`()
    for (ch in r) { if (ch == r.first()) break }
    return if (r == "kw-pos-122-5") "OK" else "NOK"
}
