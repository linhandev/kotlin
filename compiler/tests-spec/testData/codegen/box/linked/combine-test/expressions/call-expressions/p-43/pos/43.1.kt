// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 43 -> sentence 43
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 43 -> sentence 43
 *                type-inference, introduction-1 -> paragraph 43 -> sentence 43
 *                type-system, upper-and-lower-bounds -> paragraph 43 -> sentence 43
 * NUMBER: 1
 * DESCRIPTION: type parameter with upper bound can still be inferred from value arguments at call site
 */

// TESTCASE NUMBER: 1
fun <T : Comparable<T>> max(a: T, b: T): T = if (a >= b) a else b

fun box(): String {
    if (max(1, 2) != 2) return "NOK"
    if (max(3, 1) != 3) return "NOK"
    if (max("a", "b") != "b") return "NOK"
    if (max("x", "w") != "x") return "NOK"
    return "OK"
}
