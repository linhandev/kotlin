// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: LineStrRef $x in line string
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val x = 10
    return if ("val=$x" == "val=10") "OK" else "NOK"
}
