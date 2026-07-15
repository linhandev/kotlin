// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 78 -> sentence 78
 * NUMBER: 3
 * DESCRIPTION: THIS token in inner class qualified this@Outer access
 */
// TESTCASE NUMBER: 1

class OuterThis78 {
    val token = "kw-78-78-3"

    inner class Inner {
        fun value(): String = this@OuterThis78.token
    }
}

fun box(): String {
    val expected = "kw-78-78-3"
    val result = OuterThis78().Inner().value()
    if (result != expected) return "NOK"
    return "OK"
}
