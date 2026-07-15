// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 58 -> sentence 58
 * NUMBER: 2
 * DESCRIPTION: PROPERTY token on constructor parameter property @property:Suppress
 */
// TESTCASE NUMBER: 1

class PropertyParam58(@property:Suppress("UNUSED_PARAMETER") val code: String)

fun box(): String {
    val expected = "prop-58"
    if (PropertyParam58(expected).code != expected) return "NOK"
    return "OK"
}
