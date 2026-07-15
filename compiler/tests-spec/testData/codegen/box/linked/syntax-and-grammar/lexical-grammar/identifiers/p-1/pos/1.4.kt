// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: Letter in function name Übung
 */
// TESTCASE NUMBER: 1
fun Übung(): String = "codegen-1-4"
fun box(): String = if (Übung() == "codegen-1-4") "OK" else "NOK"
