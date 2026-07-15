// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 67 -> sentence 67
 * NUMBER: 3
 * DESCRIPTION: CLASS token in inner class declaration
 */
// TESTCASE NUMBER: 1

class OuterInner67 {
    inner class Inner {
        fun value(): String = "kw-67-67-3"
    }
}

fun box(): String {
    val expected = "kw-67-67-3"
    val result = OuterInner67().Inner().value()
    if (result != expected) return "NOK"
    return "OK"
}
