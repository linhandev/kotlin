// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 97 -> sentence 97
 * NUMBER: 5
 * DESCRIPTION: IN token as backtick-escaped identifier fun `in`
 */
// TESTCASE NUMBER: 1
fun `in`(flag: Boolean): String = if (flag) "OK" else "NOK"

fun box(): String = `in`(true)
