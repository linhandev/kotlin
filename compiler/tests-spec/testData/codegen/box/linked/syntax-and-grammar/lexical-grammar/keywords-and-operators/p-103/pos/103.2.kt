// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 103 -> sentence 103
 * NUMBER: 2
 * DESCRIPTION: PRIVATE token in private top-level function declaration
 */
private fun privateFn103(): String = "codegen-103-2"
// TESTCASE NUMBER: 1
fun box(): String = if (privateFn103() == "codegen-103-2") "OK" else "NOK"
