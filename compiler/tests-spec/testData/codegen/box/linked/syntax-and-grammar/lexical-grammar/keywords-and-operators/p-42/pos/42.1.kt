// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 42 -> sentence 42
 * NUMBER: 1
 * DESCRIPTION: RANGLE token in generic type List<Int>
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val list: List<Int> = listOf(42)
    return if (list.first() == 42) "OK" else "NOK"
}
