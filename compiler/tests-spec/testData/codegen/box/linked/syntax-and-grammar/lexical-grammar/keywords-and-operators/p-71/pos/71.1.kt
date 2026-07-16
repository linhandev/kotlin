// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 71 -> sentence 71
 * NUMBER: 1
 * DESCRIPTION: VAL token in top-level val property declaration
 */
// TESTCASE NUMBER: 1

val topLevel71 = "kw-71-71-1"

fun box(): String { val ok = topLevel71 == "kw-71-71-1"; return if (ok == true) "OK" else "NOK" }
