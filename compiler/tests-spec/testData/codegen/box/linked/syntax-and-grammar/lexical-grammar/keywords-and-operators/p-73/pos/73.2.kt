// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 73 -> sentence 73
 * NUMBER: 2
 * DESCRIPTION: TYPE_ALIAS token in generic typealias declaration
 */
// TESTCASE NUMBER: 1

typealias Box73<T> = List<T>

fun box(): String {
    val expected = "box-73"
    val items: Box73<String> = listOf(expected)
    if (items.single() != expected) return "NOK"
    return "OK"
}
