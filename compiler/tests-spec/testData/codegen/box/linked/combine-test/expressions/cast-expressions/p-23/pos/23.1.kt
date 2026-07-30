// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 23 -> sentence 23
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 23 -> sentence 23
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: as? Number then safe call toInt
 */

// TESTCASE NUMBER: 1
fun test(x: Any?): Int? = (x as? Number)?.toInt()

fun box(): String {
    if (test(3) != 3) return "NOK"
    if (test(2.5) != 2) return "NOK"
    if (test("x") != null) return "NOK"
    if (test(null) != null) return "NOK"
    return "OK"
}
