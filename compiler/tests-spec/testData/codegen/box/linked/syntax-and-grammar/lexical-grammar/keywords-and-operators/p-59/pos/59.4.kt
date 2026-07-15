// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 59 -> sentence 59
 * NUMBER: 4
 * DESCRIPTION: GET token in interface property getter declaration
 */
// TESTCASE NUMBER: 1

interface GetterIface59 {
    val token: String
        get() = "kw-59-59-4"
}

class GetterIfaceImpl59 : GetterIface59

fun box(): String {
    val expected = "kw-59-59-4"
    val result = GetterIfaceImpl59().token
    if (result != expected) return "NOK"
    return "OK"
}
