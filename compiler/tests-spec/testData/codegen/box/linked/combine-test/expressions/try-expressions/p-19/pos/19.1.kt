// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 19 -> sentence 19
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 19 -> sentence 19
 *                expressions, elvis-operator-expressions -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: try block with safe call and Elvis providing default Int
 */

// TESTCASE NUMBER: 1
fun test(x: String?): Int = try {
    x?.length ?: -1
} catch (e: Exception) {
    -2
}

fun box(): String {
    if (test("hi") != 2) return "NOK"
    if (test(null) != -1) return "NOK"
    if (test("") != 0) return "NOK"
    return "OK"
}
