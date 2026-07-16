// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 68 -> sentence 68
 * NUMBER: 5
 * DESCRIPTION: INTERFACE token as backtick-escaped identifier fun `interface`
 */
// TESTCASE NUMBER: 1

fun `interface`(): String = "kw-pos-68-5"

fun box(): String {
    val r = `interface`(); val t = r.takeIf { it == "kw-pos-68-5" }; return if (t != null) "OK" else "NOK"
}
