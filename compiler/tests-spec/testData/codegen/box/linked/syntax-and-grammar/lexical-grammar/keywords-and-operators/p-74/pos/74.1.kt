// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 74 -> sentence 74
 * NUMBER: 1
 * DESCRIPTION: CONSTRUCTOR token in primary constructor declaration
 */
// TESTCASE NUMBER: 1

class PrimaryCtor74(val token: String)

fun box(): String {
    val expected = "ctor-74"
    if (PrimaryCtor74(expected).token != expected) return "NOK"
    return "OK"
}
