// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 32 -> sentence 32
 * PRIMARY LINKS: expressions, additive-expressions -> paragraph 32 -> sentence 32
 *                expressions, range-expressions -> paragraph 32 -> sentence 32
 *                operator-overloading, overview -> paragraph 32 -> sentence 32
 * NUMBER: 1
 * DESCRIPTION: additive expression result used with in and not-in on IntRange at runtime
 */

// TESTCASE NUMBER: 1
fun test(): Boolean = (1 + 2) in 1..10 && (1 + 10) !in 1..5

fun box(): String {
    if (!test()) return "NOK: additive lhs with range containment"
    if ((1 + 11) in 1..10) return "NOK: sum outside range must be false"
    if (!((2 + 3) !in 1..4)) return "NOK: not-in on out-of-range sum"
    return "OK"
}
