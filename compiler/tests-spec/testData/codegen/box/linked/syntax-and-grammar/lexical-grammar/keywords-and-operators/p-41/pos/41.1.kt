// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 41 -> sentence 41
 * NUMBER: 1
 * DESCRIPTION: LANGLE token in generic type List<Int>
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val list: List<Int> = listOf(1, 2, 3)
    return if (list.size == 3) "OK" else "NOK"
}
