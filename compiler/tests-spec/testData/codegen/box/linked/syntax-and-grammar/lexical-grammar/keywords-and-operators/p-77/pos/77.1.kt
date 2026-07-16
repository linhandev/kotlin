// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 77 -> sentence 77
 * NUMBER: 1
 * DESCRIPTION: INIT token in class initializer block init
 */
// TESTCASE NUMBER: 1

class InitBlock77(val token: String) {
    init {
        check(token.startsWith("init-77"))
    }
}

fun box(): String {
    val expected = "init-77-1"
    val instance = InitBlock77(expected)
    if (instance.token != expected) return "NOK"
    return "OK"
}
