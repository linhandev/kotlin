// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 128 -> sentence 128
 * NUMBER: 5
 * DESCRIPTION: ACTUAL token used as backtick-escaped typealias name
 */
// TESTCASE NUMBER: 1
typealias `actual` = String

val value: `actual` = "codegen-128-5"
fun box(): String = if (value == "codegen-128-5") "OK" else "NOK"
