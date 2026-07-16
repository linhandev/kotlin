// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 103 -> sentence 103
 * NUMBER: 1
 * DESCRIPTION: PRIVATE token in private class declaration
 */
private class PrivateHolder103(val value: String)

// TESTCASE NUMBER: 1
fun box(): String = if (PrivateHolder103("codegen-103-1").value == "codegen-103-1") "OK" else "NOK"
