// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 122 -> sentence 122
 * NUMBER: 3
 * DESCRIPTION: LATEINIT token in lateinit var inside object
 */
// TESTCASE NUMBER: 1
object LateInitObject122 {
    lateinit var token122: String
}

fun box(): String {
    val expected = "lateinit-122-3"
    LateInitObject122.token122 = expected
    if (LateInitObject122.token122 != expected) return "NOK"
    return "OK"
}
