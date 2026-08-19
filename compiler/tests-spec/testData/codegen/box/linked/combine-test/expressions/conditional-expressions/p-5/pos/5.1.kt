// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 5 -> sentence 5
 *                type-inference, smart-casts -> paragraph 5 -> sentence 5
 *                type-system, type-kinds, union-types -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: conditional expression with is smart cast and different branch types infer common supertype Number
 */

// TESTCASE NUMBER: 1
fun test(x: Any): Number = if (x is Int) x + 1 else 0.5

fun box(): String {
    if (test(1) != 2) return "NOK"
    if (test("x") != 0.5) return "NOK"
    if (test(2.5) != 0.5) return "NOK"
    return "OK"
}
