// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 3 -> sentence 3
 * NUMBER: 4
 * DESCRIPTION: UnicodeDigit in function name test२
 */
// TESTCASE NUMBER: 1
fun test२(): String = "codegen-3-4"
fun box(): String = if (test२() == "codegen-3-4") "OK" else "NOK"
