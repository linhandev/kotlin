// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 127 -> sentence 127
 * NUMBER: 2
 * DESCRIPTION: EXPECT token used as backtick-escaped top-level property name
 */
// TESTCASE NUMBER: 1
val `expect`: String = "codegen-127-2"

fun box(): String { val ok = `expect` == "codegen-127-2"; return if (ok) "OK" else "NOK" }
