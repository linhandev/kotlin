// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 122 -> sentence 122
 * NUMBER: 4
 * DESCRIPTION: LATEINIT token in lateinit var in open class hierarchy
 */
// TESTCASE NUMBER: 1
open class LateInitBase122 {
    lateinit var label122: String
}

class LateInitDerived122 : LateInitBase122()

fun box(): String {
    val expected = "lateinit-122-4"
    val value = LateInitDerived122()
    value.label122 = expected
    if (value.label122 != expected) return "NOK"
    return "OK"
}
