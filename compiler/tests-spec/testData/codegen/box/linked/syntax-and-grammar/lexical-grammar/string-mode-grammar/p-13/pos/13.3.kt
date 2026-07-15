// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 13 -> sentence 13
 * NUMBER: 3
 * DESCRIPTION: MultiLineStrExprStart ${"OK"} string literal in multiline
 */
// TESTCASE NUMBER: 1
fun box(): String = if ("""
    result=${"OK"}
    """.trim() == "result=OK") "OK" else "NOK"
