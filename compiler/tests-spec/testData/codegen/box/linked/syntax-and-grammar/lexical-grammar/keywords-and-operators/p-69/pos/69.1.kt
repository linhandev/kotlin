// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 69 -> sentence 69
 * NUMBER: 1
 * DESCRIPTION: FUN token in top-level function declaration fun answer69
 */
// TESTCASE NUMBER: 1

fun answer69(): String = "kw-69-69-1"

fun box(): String {
    val expected = "kw-69-69-1"
    val result = answer69()
    if (result != expected) return "NOK"
    return "OK"
}
