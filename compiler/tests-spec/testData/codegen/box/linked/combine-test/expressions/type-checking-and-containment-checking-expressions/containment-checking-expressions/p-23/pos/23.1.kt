// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: expressions, logical-conjunction-expressions -> paragraph 23 -> sentence 23
 *                operator-overloading, overview -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: logical conjunction short-circuit with in operator at runtime
 */

// TESTCASE NUMBER: 1
var rhsEvaluated = false

fun test(x: Int, xs: List<Int>): Boolean {
    rhsEvaluated = false
    return x > 0 && run {
        rhsEvaluated = true
        x in xs
    }
}

fun box(): String {
    if (!test(2, listOf(1, 2, 3))) return "NOK: positive x in list"
    if (test(-1, listOf(1, 2, 3))) return "NOK: non-positive x must be false"
    if (rhsEvaluated) return "NOK: rhs must not evaluate when lhs is false"
    rhsEvaluated = false
    if (test(2, listOf(4, 5))) return "NOK: absent element"
    if (!rhsEvaluated) return "NOK: rhs must evaluate when lhs is true"
    return "OK"
}
