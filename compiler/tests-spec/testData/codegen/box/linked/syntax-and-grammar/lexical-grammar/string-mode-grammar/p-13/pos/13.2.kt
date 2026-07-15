// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 13 -> sentence 13
 * NUMBER: 2
 * DESCRIPTION: MultiLineStrExprStart ${a + b} across multiline lines
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val a = 4
    val b = 6
    return if ("""
        total=${a + b}
        done
        """.trim().startsWith("total=10")) "OK" else "NOK"
}
