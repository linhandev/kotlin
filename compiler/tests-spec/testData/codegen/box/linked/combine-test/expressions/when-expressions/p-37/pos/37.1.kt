// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 37 -> sentence 37
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 37 -> sentence 37
 *                type-inference, smart-casts -> paragraph 37 -> sentence 37
 *                type-inference, introduction-1 -> paragraph 37 -> sentence 37
 * NUMBER: 1
 * DESCRIPTION: when expression with multiple is branches smart cast to different types
 */

// TESTCASE NUMBER: 1
fun test(x: Any): Int = when (x) {
    is String -> x.length
    is Int -> x + 1
    else -> -1
}

fun box(): String {
    if (test("hello") != 5) return "NOK"
    if (test(1) != 2) return "NOK"
    if (test(1.5) != -1) return "NOK"
    return "OK"
}
