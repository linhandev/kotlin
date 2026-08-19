// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 31 -> sentence 31
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 31 -> sentence 31
 *                expressions, range-expressions -> paragraph 31 -> sentence 31
 *                type-system, introduction-1 -> paragraph 31 -> sentence 31
 *                type-inference, smart-casts -> paragraph 31 -> sentence 31
 *                expressions, conditional-expressions -> paragraph 31 -> sentence 31
 * NUMBER: 1
 * DESCRIPTION: when expression with in range branch after nullable subject is narrowed by if condition
 */

// TESTCASE NUMBER: 1
fun test(x: Int?): String = if (x != null) when (x) {
    in 1..10 -> "inside"
    else -> "other"
} else "null"

fun box(): String {
    if (test(5) != "inside") return "NOK"
    if (test(1) != "inside") return "NOK"
    if (test(10) != "inside") return "NOK"
    if (test(0) != "other") return "NOK"
    if (test(11) != "other") return "NOK"
    if (test(null) != "null") return "NOK"
    return "OK"
}
