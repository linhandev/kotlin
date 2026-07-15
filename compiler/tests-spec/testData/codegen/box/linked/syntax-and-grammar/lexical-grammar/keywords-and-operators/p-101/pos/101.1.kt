// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 101 -> sentence 101
 * NUMBER: 1
 * DESCRIPTION: DYNAMIC token as soft keyword used as function name
 */
// TESTCASE NUMBER: 1
fun dynamic(): String = "codegen-101-1"

fun box(): String { return if (dynamic() == "codegen-101-1") "OK" else "NOK" }
