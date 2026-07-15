// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 73 -> sentence 73
 * NUMBER: 1
 * DESCRIPTION: TYPE_ALIAS token in simple typealias declaration
 */
// TESTCASE NUMBER: 1

typealias Label73 = String

fun box(): String {
    val expected = "alias-73"
    val value: Label73 = expected
    if (value != expected) return "NOK"
    return "OK"
}
