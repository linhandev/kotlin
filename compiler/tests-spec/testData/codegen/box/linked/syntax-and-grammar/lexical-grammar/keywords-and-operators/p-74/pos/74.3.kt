// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 74 -> sentence 74
 * NUMBER: 3
 * DESCRIPTION: CONSTRUCTOR token in secondary constructor with body block
 */
// TESTCASE NUMBER: 1

class CtorBody74 {
    val token: String

    constructor(label: String) {
        token = label
    }
}

fun box(): String {
    val expected = "ctor-74-3"
    if (CtorBody74(expected).token != expected) return "NOK"
    return "OK"
}
