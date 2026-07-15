// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 96 -> sentence 96
 * NUMBER: 4
 * DESCRIPTION: IS token as backtick-escaped identifier fun `is`
 */
// TESTCASE NUMBER: 1
fun `is`(flag: Boolean): String = if (flag) "OK" else "NOK"

fun box(): String = `is`(true)
