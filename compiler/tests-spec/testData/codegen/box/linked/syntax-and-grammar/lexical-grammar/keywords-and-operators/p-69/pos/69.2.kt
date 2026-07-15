// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 69 -> sentence 69
 * NUMBER: 2
 * DESCRIPTION: FUN token in class member function declaration
 */
// TESTCASE NUMBER: 1

class MemberFun69 {
    fun value(): String = "kw-69-69-2"
}

fun box(): String {
    val expected = "kw-69-69-2"
    val result = MemberFun69().value()
    if (result != expected) return "NOK"
    return "OK"
}
