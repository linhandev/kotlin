// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 70 -> sentence 70
 * NUMBER: 5
 * DESCRIPTION: OBJECT token as backtick-escaped identifier fun `object`
 */
// TESTCASE NUMBER: 1

fun `object`(): String = "kw-pos-70-5"

fun box(): String {
    val r = `object`(); return if (r.hashCode() != "kw-pos-70-5".hashCode()) "NOK" else "OK"
}
