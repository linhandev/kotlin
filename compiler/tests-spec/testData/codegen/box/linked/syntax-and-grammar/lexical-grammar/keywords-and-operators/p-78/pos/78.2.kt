// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 78 -> sentence 78
 * NUMBER: 2
 * DESCRIPTION: THIS token in constructor delegation this() call
 */
// TESTCASE NUMBER: 1

class ThisCtor78 {
    val label: String

    constructor() : this("kw-78-78-2")

    constructor(token: String) {
        label = token
    }
}

fun box(): String {
    val expected = "kw-78-78-2"
    val result = ThisCtor78().label
    if (result != expected) return "NOK"
    return "OK"
}
