// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 32 -> sentence 32
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 32 -> sentence 32
 *                expressions, range-expressions -> paragraph 32 -> sentence 32
 *                type-system, introduction-1 -> paragraph 32 -> sentence 32
 *                expressions, elvis-operator-expressions -> paragraph 32 -> sentence 32
 * NUMBER: 1
 * DESCRIPTION: when expression with Elvis operator on nullable subject before range containment branch
 */

// TESTCASE NUMBER: 1
fun test(x: Int?): String = when (x ?: -1) {
    in 1..10 -> "inside"
    else -> "other"
}

fun box(): String {
    if (test(5) != "inside") return "NOK"
    if (test(1) != "inside") return "NOK"
    if (test(10) != "inside") return "NOK"
    if (test(null) != "other") return "NOK"
    if (test(0) != "other") return "NOK"
    if (test(11) != "other") return "NOK"
    return "OK"
}
