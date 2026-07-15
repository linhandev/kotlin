// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 68 -> sentence 68
 * NUMBER: 2
 * DESCRIPTION: INTERFACE token in interface with property and function members
 */
// TESTCASE NUMBER: 1

interface MemberIface68 {
    val token: String
        get() = "iface-68-2"
    fun check(expected: String): Boolean = token == expected
}

class MemberIface68Impl : MemberIface68

fun box(): String {
    val expected = "iface-68-2"
    if (!MemberIface68Impl().check(expected)) return "NOK"
    return "OK"
}
