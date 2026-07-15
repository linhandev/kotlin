// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: FieldIdentifier $name in line string
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val name = "Kotlin"
    return if ("Hello $name" == "Hello Kotlin") "OK" else "NOK"
}
