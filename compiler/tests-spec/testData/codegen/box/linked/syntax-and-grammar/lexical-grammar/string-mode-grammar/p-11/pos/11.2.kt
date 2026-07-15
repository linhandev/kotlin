// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 11 -> sentence 11
 * NUMBER: 2
 * DESCRIPTION: MultiLineStrRef soft keyword field in multiline string
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val field = 42
    return if ("""
        value=$field
        """.trim() == "value=42") "OK" else "NOK"
}
