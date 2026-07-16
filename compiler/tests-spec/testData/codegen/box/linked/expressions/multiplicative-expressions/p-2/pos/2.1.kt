// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, multiplicative-expressions -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: times divide and remainder resolve to overloadable operator functions
 */

// TESTCASE NUMBER: 1

fun box(): String {
    if (2 * 3 != 6) return "NOK"
    if (6 / 2 != 3) return "NOK"
    if (7 % 3 != 1) return "NOK"
    return "OK"
}
