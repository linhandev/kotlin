// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: FieldIdentifier soft keyword field as IdentifierOrSoftKey
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val field = 42
    check("value=$field" == "value=42")
    return "OK"
}
