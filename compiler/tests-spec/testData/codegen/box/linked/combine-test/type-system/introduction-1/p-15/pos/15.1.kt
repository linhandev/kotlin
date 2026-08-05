// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 15 -> sentence 15
 *                expressions, when-expressions -> paragraph 15 -> sentence 15
 *                type-inference, introduction-1 -> paragraph 15 -> sentence 15
 *                type-system, type-kinds, flexible-types, platform-types -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: when on a Java List<*> uses is List<*> smart cast and joins with Int under Number
 */

// TESTCASE NUMBER: 1
fun test56215(x: Any): Number = when (x) {
    is java.util.List<*> -> x.size
    is Int -> x
    else -> -1
}

fun box(): String {
    if (test56215(java.util.ArrayList(listOf(1, 2))).toInt() != 2) return "NOK"
    if (test56215(7).toInt() != 7) return "NOK"
    if (test56215("x").toInt() != -1) return "NOK"
    return "OK"
}
