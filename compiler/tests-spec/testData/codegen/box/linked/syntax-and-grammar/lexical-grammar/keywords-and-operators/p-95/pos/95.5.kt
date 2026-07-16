// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 95 -> sentence 95
 * NUMBER: 5
 * DESCRIPTION: AS token as backtick-escaped identifier fun `as`
 */
// TESTCASE NUMBER: 1
fun `as`(value: String): String = value

fun box(): String { return if (`as`("codegen-95-5") == "codegen-95-5") "OK" else "NOK" }
