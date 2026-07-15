// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 11 -> sentence 11
 * NUMBER: 3
 * DESCRIPTION: MultiLineStrRef multiple references across lines
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val a = 1
    val b = 2
    val c = 3
    return if ("""$a
$b
$c""".lines().size == 3) "OK" else "NOK"
}
