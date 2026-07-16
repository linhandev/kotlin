// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, comparison-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: comparison operators expand to compareTo for non-floating-point types
 */

// TESTCASE NUMBER: 1

fun box(): String {
    if (!(1 < 2)) return "NOK"
    if (!(2 > 1)) return "NOK"
    if (!(1 <= 1)) return "NOK"
    if (!(2 >= 1)) return "NOK"
    return "OK"
}
