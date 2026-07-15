// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 68 -> sentence 68
 * NUMBER: 1
 * DESCRIPTION: INTERFACE token in simple interface declaration interface I
 */
// TESTCASE NUMBER: 1

interface SimpleIface68 {
    fun value(): String
}

class SimpleIface68Impl : SimpleIface68 {
    override fun value(): String = "kw-68-68-1"
}

fun box(): String {
    val expected = "kw-68-68-1"
    val result = SimpleIface68Impl().value()
    if (result != expected) return "NOK"
    return "OK"
}
