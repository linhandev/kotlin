// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 73 -> sentence 73
 * NUMBER: 4
 * DESCRIPTION: TYPE_ALIAS token in chained typealias declarations
 */
// TESTCASE NUMBER: 1

typealias Count73 = Int
typealias Score73 = Count73

fun box(): String {
    val value: Score73 = 42
    return if (value == 42) "OK" else "NOK"
}
