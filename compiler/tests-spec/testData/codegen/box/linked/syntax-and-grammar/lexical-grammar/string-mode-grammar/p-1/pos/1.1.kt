// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: QUOTE_OPEN line string literal hello
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val s = "hello"
    if (s.length != 5) return "NOK"
    return if (s.uppercase() == "HELLO") "OK" else "NOK"
}
