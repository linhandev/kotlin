// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, comparison-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: floating point comparison uses ieee754Less and ieee754Equals intrinsics
 */

// TESTCASE NUMBER: 1

fun box(): String {
    if (!(1.0 < 2.0)) return "NOK"
    if (!(2.0 > 1.0)) return "NOK"
    if (!(1.0 <= 1.0)) return "NOK"
    if (!(2.0 >= 1.0)) return "NOK"
    if (!(0.0 <= -0.0)) return "NOK5"
    return "OK"
}
