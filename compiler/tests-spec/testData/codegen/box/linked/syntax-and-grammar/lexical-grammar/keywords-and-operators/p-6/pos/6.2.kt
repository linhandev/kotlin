// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 6 -> sentence 6
 * NUMBER: 2
 * DESCRIPTION: LSQUARE token used in list indexing list[0]
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val list = listOf("a", "b", "c")
    return if (list[0] == "a" && list[1] == "b" && list[2] == "c") "OK" else "NOK"
}
