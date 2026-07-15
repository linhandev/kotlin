// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 5 -> sentence 5
 * NUMBER: 2
 * DESCRIPTION: LineStrRef soft keyword receiver in line string
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val receiver = "ok"
    val s = "r=$receiver"
    return if (s == "r=ok") "OK" else "NOK"
}
