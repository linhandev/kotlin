// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 63 -> sentence 63
 * NUMBER: 5
 * DESCRIPTION: SETPARAM token as backtick-escaped identifier fun `setparam`
 */

fun `setparam`(): String = "OK"

// TESTCASE NUMBER: 1
fun box(): String {
    return `setparam`()
}
