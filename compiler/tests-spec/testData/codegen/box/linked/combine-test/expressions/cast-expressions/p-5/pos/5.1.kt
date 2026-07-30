// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 5 -> sentence 5
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 5 -> sentence 5
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: as? with safe call
 */

// TESTCASE NUMBER: 1
fun test(x: Any): Int? = (x as? String)?.length

fun box(): String {
    if (test("hi") != 2) return "NOK"
    if (test(1) != null) return "NOK"
    if (test("") != 0) return "NOK"
    return "OK"
}
