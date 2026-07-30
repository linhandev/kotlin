// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 19 -> sentence 19
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: safe call also returns same instance or null
 */

// TESTCASE NUMBER: 1
class Box(val value: Int)

fun test(box: Box?): Box? = box?.also { it.value }

fun box(): String {
    val b = Box(7)
    if (test(b) !== b) return "NOK"
    if (test(null) != null) return "NOK"
    return "OK"
}
