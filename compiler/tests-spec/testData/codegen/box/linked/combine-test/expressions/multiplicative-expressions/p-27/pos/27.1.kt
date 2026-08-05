// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 27 -> sentence 27
 *                type-system, built-in-integer-types -> paragraph 27 -> sentence 27
 *                statements, assignments, operator-assignments -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: built-in Long augmented assignment times-equals desugars to times
 */

// TESTCASE NUMBER: 1
fun test(): Long {
    var x = 2L
    x *= 3L
    return x
}

fun box(): String {
    if (test() != 6L) return "NOK"
    var y = 5L
    y *= 4L
    if (y != 20L) return "NOK"
    var z = -3L
    z *= 2L
    if (z != -6L) return "NOK"
    return "OK"
}
