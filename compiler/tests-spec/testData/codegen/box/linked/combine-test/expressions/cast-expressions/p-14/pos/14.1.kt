// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 14 -> sentence 14
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 14 -> sentence 14
 *                type-inference, smart-casts -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: is on String? distinguishes null from String
 */

// TESTCASE NUMBER: 1
fun test(x: String?): Int = when {
    x is String -> x.length
    else -> 0
}

fun box(): String {
    if (test("hi") != 2) return "NOK"
    if (test(null) != 0) return "NOK"
    if (test("") != 0) return "NOK"
    return "OK"
}
