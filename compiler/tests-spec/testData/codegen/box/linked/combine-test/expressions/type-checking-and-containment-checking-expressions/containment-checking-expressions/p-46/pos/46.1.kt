// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 46 -> sentence 46
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 46 -> sentence 46
 *                expressions, range-expressions -> paragraph 46 -> sentence 46
 * NUMBER: 1
 * DESCRIPTION: in operator on ClosedFloatingPointRange uses range contains convention at runtime
 */

// TESTCASE NUMBER: 1
fun test(x: Double): Boolean = x in 1.0..2.0

fun box(): String {
    if (!test(1.5)) return "NOK: inside range"
    if (test(0.5)) return "NOK: below range"
    if (test(2.5)) return "NOK: above range"
    if (!test(1.0)) return "NOK: lower bound inclusive"
    if (!test(2.0)) return "NOK: upper bound inclusive"
    if (test(1.5) != (1.0..2.0).contains(1.5)) return "NOK: in not equivalent to range contains"
    return "OK"
}
