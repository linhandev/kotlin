// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 68 -> sentence 68
 * NUMBER: 3
 * DESCRIPTION: INTERFACE token in generic interface declaration
 */
// TESTCASE NUMBER: 1

interface GenericIface68<T> {
    fun payload(): T
}

class GenericIface68Impl : GenericIface68<String> {
    override fun payload(): String = "kw-68-68-3"
}

fun box(): String {
    val expected = "kw-68-68-3"
    val result = GenericIface68Impl().payload()
    if (result != expected) return "NOK"
    return "OK"
}
