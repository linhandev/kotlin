// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 7 -> sentence 7
 * NUMBER: 3
 * DESCRIPTION: LineStrEscapedChar UniCharacterLiteral \u0041
 */
// TESTCASE NUMBER: 1
fun box(): String = if ("\u0041" == "A") "OK" else "NOK"
