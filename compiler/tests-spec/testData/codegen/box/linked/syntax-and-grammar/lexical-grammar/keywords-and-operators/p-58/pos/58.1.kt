// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 58 -> sentence 58
 * NUMBER: 1
 * DESCRIPTION: PROPERTY token in use-site annotation @property:Suppress on property
 */
// TESTCASE NUMBER: 1

class PropertyHolder58 {
    @property:Suppress("UNUSED_VARIABLE")
    val message = "kw-58-58-1"
}

fun box(): String {
    val expected = "kw-58-58-1"
    val result = PropertyHolder58().message
    if (result != expected) return "NOK"
    return "OK"
}
