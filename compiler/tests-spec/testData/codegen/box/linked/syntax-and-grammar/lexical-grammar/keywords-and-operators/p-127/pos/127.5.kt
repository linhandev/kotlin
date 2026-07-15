// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 127 -> sentence 127
 * NUMBER: 5
 * DESCRIPTION: EXPECT token used as backtick-escaped typealias name
 */
// TESTCASE NUMBER: 1
typealias `expect` = String

val value: `expect` = "codegen-127-5"

fun box(): String { return if (value == "codegen-127-5") "OK" else "NOK" }
