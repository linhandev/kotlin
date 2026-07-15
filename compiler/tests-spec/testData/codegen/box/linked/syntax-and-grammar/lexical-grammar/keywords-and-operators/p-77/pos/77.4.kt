// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 77 -> sentence 77
 * NUMBER: 4
 * DESCRIPTION: INIT token in object declaration initializer block
 */
// TESTCASE NUMBER: 1

object InitObject77 {
    val token: String

    init {
        token = "kw-77-77-4"
    }
}

fun box(): String {
    val expected = "kw-77-77-4"
    val result = InitObject77.token
    if (result != expected) return "NOK"
    return "OK"
}
