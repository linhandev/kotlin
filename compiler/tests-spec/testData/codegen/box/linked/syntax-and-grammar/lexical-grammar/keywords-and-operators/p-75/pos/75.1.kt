// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 75 -> sentence 75
 * NUMBER: 1
 * DESCRIPTION: BY token in delegated property val by lazy
 */
// TESTCASE NUMBER: 1

class ByLazy75 {
    val token: String by lazy { "kw-75-75-1" }
}

fun box(): String {
    val expected = "kw-75-75-1"
    val result = ByLazy75().token
    if (result != expected) return "NOK"
    return "OK"
}
