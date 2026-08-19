// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 28 -> sentence 28
 *                type-system, built-in-integer-types -> paragraph 28 -> sentence 28
 *                statements, assignments, operator-assignments -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: custom type operator fun timesAssign(Long) in augmented assignment
 */

// TESTCASE NUMBER: 1
class Acc(var v: Long) {
    operator fun timesAssign(k: Long) {
        v *= k
    }
}

fun test(): Long {
    val a = Acc(2L)
    a *= 5L
    return a.v
}

fun box(): String {
    if (test() != 10L) return "NOK"
    val b = Acc(3L)
    b *= 4L
    if (b.v != 12L) return "NOK"
    val c = Acc(-2L)
    c *= 5L
    if (c.v != -10L) return "NOK"
    return "OK"
}
