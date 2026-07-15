// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 77 -> sentence 77
 * NUMBER: 3
 * DESCRIPTION: INIT token in init block assigning property from constructor parameter
 */
// TESTCASE NUMBER: 1

class InitAssign77(code: Int) {
    val doubled: Int

    init {
        doubled = code * 2
    }
}

fun box(): String {
    return if (InitAssign77(21).doubled == 42) "OK" else "NOK"
}
