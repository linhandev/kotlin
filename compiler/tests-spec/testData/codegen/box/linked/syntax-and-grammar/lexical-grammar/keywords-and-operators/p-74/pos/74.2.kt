// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 74 -> sentence 74
 * NUMBER: 2
 * DESCRIPTION: CONSTRUCTOR token in secondary constructor declaration
 */
// TESTCASE NUMBER: 1

class SecondaryCtor74(val token: String) {
    constructor(code: Int) : this(if (code == 1) "kw-74-74-2" else "NOK")
}

fun box(): String {
    val expected = "kw-74-74-2"
    val result = SecondaryCtor74(1).token
    if (result != expected) return "NOK"
    return "OK"
}
