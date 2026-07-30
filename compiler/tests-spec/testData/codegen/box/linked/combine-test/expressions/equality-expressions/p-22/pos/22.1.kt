// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: nullable Double compared with Any? uses structural equality for NaN and signed zeros
 */

// TESTCASE NUMBER: 1
fun same(a: Double?, b: Any?): Boolean = a == b

fun test(): Boolean =
    same(Double.NaN, Double.NaN) &&
            !same(0.0, -0.0) &&
            same(null, null) &&
            !same(null, 0.0)

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}
