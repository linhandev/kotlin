// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 23 -> sentence 23
 *                expressions, range-expressions -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: when expression branch with !in range containment condition
 */

// TESTCASE NUMBER: 1
fun test(x: Int): String = when (x) {
    !in 1..10 -> "outside"
    else -> "inside"
}

fun box(): String {
    if (test(0) != "outside") return "NOK"
    if (test(11) != "outside") return "NOK"
    if (test(5) != "inside") return "NOK"
    if (test(10) != "inside") return "NOK"
    return "OK"
}
