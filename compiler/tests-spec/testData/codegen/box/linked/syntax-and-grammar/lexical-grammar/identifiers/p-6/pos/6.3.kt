// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 6 -> sentence 6
 * NUMBER: 3
 * DESCRIPTION: Soft keyword set used as function name without escaping
 */
// TESTCASE NUMBER: 1
fun set(): String = "codegen-6-3"

fun box(): String { when (set() == "codegen-6-3") { true -> return "OK"; else -> return "NOK" } }
