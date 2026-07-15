// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 67 -> sentence 67
 * NUMBER: 5
 * DESCRIPTION: CLASS token as backtick-escaped identifier fun `class`
 */
// TESTCASE NUMBER: 1

fun `class`(): String = "kw-pos-67-5"

fun box(): String {
    val r = `class`(); return when (r) {"kw-pos-67-5" -> "OK"; else -> "NOK"}
}
