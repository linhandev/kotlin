// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: MultiLineStrRef $name in multiline string
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val name = "Kotlin"
    return if ("""Hello
$name""".trim() == "Hello\nKotlin") "OK" else "NOK"
}
