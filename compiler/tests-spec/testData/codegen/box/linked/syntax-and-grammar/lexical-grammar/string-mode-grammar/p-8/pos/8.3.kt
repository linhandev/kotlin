// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 8 -> sentence 8
 * NUMBER: 3
 * DESCRIPTION: LineStrExprStart ${"OK"} string literal expression
 */
// TESTCASE NUMBER: 1
fun box(): String = if ("result=${"OK"}" == "result=OK") "OK" else "NOK"
