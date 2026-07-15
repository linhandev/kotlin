// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 124 -> sentence 124
 * NUMBER: 5
 * DESCRIPTION: NOINLINE token as backtick-escaped identifier fun `noinline`
 */
// TESTCASE NUMBER: 1
fun `noinline`(): String = "kw-pos-124-5"

fun box(): String {
    val r = `noinline`()
    return when {
        r == "kw-pos-124-5" -> "OK"
        else -> "NOK"
    }
}
