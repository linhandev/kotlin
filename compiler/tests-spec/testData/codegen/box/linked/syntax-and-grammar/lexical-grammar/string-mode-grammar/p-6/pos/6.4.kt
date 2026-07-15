// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 6 -> sentence 6
 * NUMBER: 4
 * DESCRIPTION: LineStrText escaped dollar sign as text
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val s = "price $$100"
    if (s.count { it == '$' } != 2) return "NOK"
    return if (s == "price $$100") "OK" else "NOK"
}
