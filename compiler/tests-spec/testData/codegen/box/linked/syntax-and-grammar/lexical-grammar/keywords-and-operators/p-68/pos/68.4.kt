// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 68 -> sentence 68
 * NUMBER: 4
 * DESCRIPTION: INTERFACE token in nested interface inside class
 */
// TESTCASE NUMBER: 1

class OuterIface68 {
    interface Nested {
        fun value(): String
    }

    class NestedImpl : Nested {
        override fun value(): String = "kw-68-68-4"
    }
}

fun box(): String {
    val expected = "kw-68-68-4"
    val result = OuterIface68.NestedImpl().value()
    if (result != expected) return "NOK"
    return "OK"
}
