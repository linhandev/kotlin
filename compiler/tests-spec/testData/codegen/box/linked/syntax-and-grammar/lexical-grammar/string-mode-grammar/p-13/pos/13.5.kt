// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 13 -> sentence 13
 * NUMBER: 5
 * DESCRIPTION: MultiLineStrExprStart nested template inside multiline expression
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val part = "X"
    return if ("""
        ${"Y$part"}
        """.trim() == "YX") "OK" else "NOK"
}
