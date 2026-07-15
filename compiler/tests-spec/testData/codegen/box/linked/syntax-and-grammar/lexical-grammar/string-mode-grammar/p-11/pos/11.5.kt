// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 11 -> sentence 11
 * NUMBER: 5
 * DESCRIPTION: MultiLineStrRef receiver soft keyword at multiline start
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val receiver = "ok"
    return if ("""$receiver
line2""" == "ok\nline2") "OK" else "NOK"
}
