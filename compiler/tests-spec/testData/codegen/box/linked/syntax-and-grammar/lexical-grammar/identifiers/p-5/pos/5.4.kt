// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 5 -> sentence 5
 * NUMBER: 4
 * DESCRIPTION: Soft keyword get used as function name without escaping
 */
// TESTCASE NUMBER: 1
fun get(): String = "codegen-5-4"

fun box(): String { val ok = get() == "codegen-5-4"; return ok.let { if (it) "OK" else "NOK" } }
