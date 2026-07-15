// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 3 -> sentence 3
 * NUMBER: 5
 * DESCRIPTION: FieldIdentifier escaped hard keyword `if`
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val `if` = "yes"
    return if ("result=$`if`" == "result=yes") "OK" else "NOK"
}
