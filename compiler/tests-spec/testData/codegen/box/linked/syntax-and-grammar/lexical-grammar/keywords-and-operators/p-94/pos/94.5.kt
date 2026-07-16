// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 94 -> sentence 94
 * NUMBER: 5
 * DESCRIPTION: BREAK token as backtick-escaped identifier fun `break`
 */
fun `break`(): String = "OK"

// TESTCASE NUMBER: 1
fun box(): String = `break`()
