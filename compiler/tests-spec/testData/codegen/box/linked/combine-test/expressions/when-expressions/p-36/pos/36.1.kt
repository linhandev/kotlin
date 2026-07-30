// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 36 -> sentence 36
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 36 -> sentence 36
 *                type-inference, smart-casts -> paragraph 36 -> sentence 36
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 36 -> sentence 36
 * NUMBER: 1
 * DESCRIPTION: when expression with is branch smart cast for property access
 */

// TESTCASE NUMBER: 1
fun test(x: Any): Int = when (x) {
    is String -> x.length
    else -> -1
}

fun box(): String {
    if (test("hello") != 5) return "NOK"
    if (test(123) != -1) return "NOK"
    if (test("") != 0) return "NOK"
    return "OK"
}
