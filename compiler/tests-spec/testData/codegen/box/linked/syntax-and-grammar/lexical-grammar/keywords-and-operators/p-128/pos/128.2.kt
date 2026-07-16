// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 128 -> sentence 128
 * NUMBER: 2
 * DESCRIPTION: ACTUAL token used as backtick-escaped top-level property name
 */
// TESTCASE NUMBER: 1
val `actual`: String = "codegen-128-2"
fun box(): String = if (`actual` == "codegen-128-2") "OK" else "NOK"
