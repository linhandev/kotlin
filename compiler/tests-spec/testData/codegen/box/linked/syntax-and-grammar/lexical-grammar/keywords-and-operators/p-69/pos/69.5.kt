// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 69 -> sentence 69
 * NUMBER: 5
 * DESCRIPTION: FUN token as backtick-escaped identifier fun `fun`
 */
// TESTCASE NUMBER: 1

fun `fun`(): String = "kw-pos-69-5"

fun box(): String {
    val r = `fun`(); val t = r.takeUnless { it != "kw-pos-69-5" }; return if (t != null) "OK" else "NOK"
}
