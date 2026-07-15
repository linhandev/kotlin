// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 62 -> sentence 62
 * NUMBER: 2
 * DESCRIPTION: PARAM token in use-site annotation @param:Suppress on non-property constructor parameter
 */
// TESTCASE NUMBER: 1

class PlainParam62(@param:Suppress("UNUSED_PARAMETER") name: String) {
    fun label(): String = "kw-62-62-2"
}

fun box(): String {
    val expected = "kw-62-62-2"
    val result = PlainParam62("test").label()
    if (result != expected) return "NOK"
    return "OK"
}
