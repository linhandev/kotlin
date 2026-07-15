// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 3 -> sentence 3
 * NUMBER: 4
 * DESCRIPTION: Multiple FieldIdentifier references in one string
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val a = 1
    val b = 2
    val s = "$a plus $b"
    return if (s == "1 plus 2") "OK" else "NOK"
}
