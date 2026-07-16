// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 103 -> sentence 103
 * NUMBER: 3
 * DESCRIPTION: PRIVATE token in private top-level property declaration
 */
private val privateVal103: String = "codegen-103-3"
// TESTCASE NUMBER: 1
fun box(): String { check(privateVal103 == "codegen-103-3"); return "OK" }
