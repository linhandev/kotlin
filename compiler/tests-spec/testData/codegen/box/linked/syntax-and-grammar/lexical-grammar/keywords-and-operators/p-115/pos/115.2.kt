// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 115 -> sentence 115
 * NUMBER: 2
 * DESCRIPTION: EXTERNAL token in external member function declaration coexists with regular member function and property in class
 */
// TESTCASE NUMBER: 1
class NativeWrapper115 {
    external fun nativeValue(): String
    fun regularValue(): String = "external-member-115-2"
    val prop: Int = 42
}

fun box(): String {
    val expected = "external-member-115-2"
    val instance = NativeWrapper115()
    if (instance.regularValue() != expected || instance.prop != 42) return "NOK"
    return "OK"
}