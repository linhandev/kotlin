// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, equality-expressions, value-equality-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: floating point value equality uses ieee754Equals intrinsic
 */

// TESTCASE NUMBER: 1

fun box(): String {
    if (!(1.0 == 1.0)) return "NOK"
    if (!(0.0 == -0.0)) return "NOK"
    return "OK"
}
