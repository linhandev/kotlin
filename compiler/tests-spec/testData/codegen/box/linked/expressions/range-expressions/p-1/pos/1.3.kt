// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, range-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: range operators .. and ..< resolve to overloadable operator functions
 */

// TESTCASE NUMBER: 1

fun box(): String {
    if (2 !in 1..3) return "NOK"
    if (3 in 1..<3) return "NOK"
    if (2 !in 1..<3) return "NOK"
    return "OK"
}
