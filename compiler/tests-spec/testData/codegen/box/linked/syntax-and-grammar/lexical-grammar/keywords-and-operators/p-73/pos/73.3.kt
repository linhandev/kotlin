// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 73 -> sentence 73
 * NUMBER: 3
 * DESCRIPTION: TYPE_ALIAS token in function type typealias declaration
 */
// TESTCASE NUMBER: 1

typealias Handler73 = (String) -> String

fun box(): String {
    val expected = "typealias-73-3"
    val handler: Handler73 = { input -> input }
    if (handler(expected) != expected) return "NOK"
    return "OK"
}
