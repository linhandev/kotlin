// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: COMMA token used as separator in list construction listOf(1, 2, 3)
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val list = listOf(1, 2, 3, 4, 5)
    return if (list.size == 5 && list[0] == 1 && list[4] == 5) "OK" else "NOK"
}
