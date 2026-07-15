// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 6 -> sentence 6
 * NUMBER: 3
 * DESCRIPTION: LineStrText standalone dollar sign not starting template
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val s = "cost is $5"
    if (s.indexOf('$') != 8) return "NOK"
    return if (s == "cost is $5") "OK" else "NOK"
}
