// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 20 -> sentence 20
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: as String then property access
 */

// TESTCASE NUMBER: 1
fun test(x: Any): Int = (x as String).length

fun box(): String {
    if (test("hi") != 2) return "NOK"
    try {
        test(1)
        return "NOK"
    } catch (_: ClassCastException) {
    }
    return "OK"
}
