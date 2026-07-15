// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 81 -> sentence 81
 * NUMBER: 1
 * DESCRIPTION: WHERE token in generic function type constraint clause
 */
// TESTCASE NUMBER: 1

fun <T> first81(list: List<T>): T where T : Comparable<T> = list.first()

fun box(): String {
    val expected = "where-81-1"
    if (first81(listOf(expected)) != expected) return "NOK"
    return "OK"
}
