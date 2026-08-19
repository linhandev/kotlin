// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 4 -> sentence 4
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 4 -> sentence 4
 *                type-inference, introduction-1 -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: safe call on nullable receiver returns nullable type even when member itself is non-null
 */

// TESTCASE NUMBER: 1
fun test(s: String?): Int? = s?.length

fun box(): String {
    val r1: Int? = test("hello")
    if (r1 != 5) return "NOK"
    val r2: Int? = test(null)
    if (r2 != null) return "NOK"
    return "OK"
}
