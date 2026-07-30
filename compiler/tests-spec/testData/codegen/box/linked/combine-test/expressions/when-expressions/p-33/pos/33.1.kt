// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 33 -> sentence 33
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 33 -> sentence 33
 *                expressions, range-expressions -> paragraph 33 -> sentence 33
 *                type-inference, introduction-1 -> paragraph 33 -> sentence 33
 * NUMBER: 1
 * DESCRIPTION: when expression with range branch and else branch infers common Comparable<*>
 */

// TESTCASE NUMBER: 1
fun test(x: Int): Comparable<*> = when (x) {
    in 1..10 -> 1
    else -> "other"
}

fun box(): String {
    if (test(5) != 1) return "NOK"
    if (test(0) != "other") return "NOK"
    if (test(11) != "other") return "NOK"
    return "OK"
}
