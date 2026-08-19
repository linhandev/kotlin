// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 3 -> sentence 3
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: safe call assigned to explicit Int? on both paths
 */

// TESTCASE NUMBER: 1

class Box(val value: Int)

fun test(box: Box?): Int? {
    val r: Int? = box?.value
    return r
}

fun box(): String {
    val a: Int? = test(Box(1))
    if (a != 1) return "NOK"
    val b: Int? = test(null)
    if (b != null) return "NOK"
    return "OK"
}
