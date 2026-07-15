// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 100 -> sentence 100
 * NUMBER: 5
 * DESCRIPTION: OUT token as backtick-escaped identifier fun `out`
 */
// TESTCASE NUMBER: 1
fun `out`(value: String): String = value

fun box(): String { val ok = `out`("codegen-100-5") == "codegen-100-5"; return ok.takeIf { it }?.let { "OK" } ?: "NOK" }
