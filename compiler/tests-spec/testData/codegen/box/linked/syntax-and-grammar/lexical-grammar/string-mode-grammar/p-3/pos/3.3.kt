// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 3 -> sentence 3
 * NUMBER: 3
 * DESCRIPTION: FieldIdentifier with underscore identifier
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val user_name = "spec"
    val expected = "id=spec"
    if ("id=$user_name" != expected) return "NOK"
    return "OK"
}
