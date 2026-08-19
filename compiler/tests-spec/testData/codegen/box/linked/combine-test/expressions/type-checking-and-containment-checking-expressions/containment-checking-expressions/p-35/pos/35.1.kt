// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 35 -> sentence 35
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 35 -> sentence 35
 *                expressions, range-expressions -> paragraph 35 -> sentence 35
 * NUMBER: 1
 * DESCRIPTION: in operator on IntRange uses range contains convention at runtime
 */

// TESTCASE NUMBER: 1
fun test(x: Int): Boolean = x in 1..10

fun box(): String {
    if (!test(5)) return "NOK: inside range"
    if (test(11)) return "NOK: outside range"
    if (!test(1)) return "NOK: lower bound inclusive"
    if (!test(10)) return "NOK: upper bound inclusive"
    if (test(5) != (1..10).contains(5)) return "NOK: in not equivalent to range contains for inside value"
    if (test(0) != (1..10).contains(0)) return "NOK: in not equivalent to range contains for outside value"
    return "OK"
}
