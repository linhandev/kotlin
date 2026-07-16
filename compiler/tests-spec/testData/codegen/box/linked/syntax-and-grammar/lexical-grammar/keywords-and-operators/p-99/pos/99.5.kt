// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 99 -> sentence 99
 * NUMBER: 5
 * DESCRIPTION: NOT_IN token with NL before !in on next line
 */
// TESTCASE NUMBER: 1
fun multilineNotIn99(value: Int): String {
    return if (value
        !in 1..100) "OK" else "NOK"
}

fun box(): String = multilineNotIn99(200)
