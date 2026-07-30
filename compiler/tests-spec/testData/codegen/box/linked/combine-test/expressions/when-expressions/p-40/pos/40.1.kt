// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 40 -> sentence 40
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 40 -> sentence 40
 *                type-system, introduction-1 -> paragraph 40 -> sentence 40
 *                type-inference, smart-casts -> paragraph 40 -> sentence 40
 * NUMBER: 1
 * DESCRIPTION: when expression with nullable subject is branch smart cast to non-null target type
 */

// TESTCASE NUMBER: 1
fun test(x: Any?): Int = when (x) {
    is String -> x.length
    null -> 0
    else -> -1
}

fun box(): String {
    if (test("hi") != 2) return "NOK"
    if (test(null) != 0) return "NOK"
    if (test(123) != -1) return "NOK"
    return "OK"
}
