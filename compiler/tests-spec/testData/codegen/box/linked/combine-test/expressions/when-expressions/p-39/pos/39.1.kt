// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 39 -> sentence 39
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 39 -> sentence 39
 *                type-inference, smart-casts -> paragraph 39 -> sentence 39
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 39 -> sentence 39
 * NUMBER: 1
 * DESCRIPTION: when is branch smart cast then parameterized member calls
 */

// TESTCASE NUMBER: 1
fun test(x: Any): Boolean = when (x) {
    is String -> x.startsWith("h") && x.endsWith("o")
    else -> false
}

fun box(): String {
    if (!test("hello")) return "NOK"
    if (test("world")) return "NOK"
    if (test(123)) return "NOK"
    if (test("")) return "NOK"
    return "OK"
}
