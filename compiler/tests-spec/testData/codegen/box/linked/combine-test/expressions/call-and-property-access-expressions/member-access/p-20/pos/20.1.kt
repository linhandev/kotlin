// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 20 -> sentence 20
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: takeIf keeps positive; drops non-positive and null receiver
 */

// TESTCASE NUMBER: 1

class Box(val value: Int)

fun test(box: Box?): Box? = box?.takeIf { it.value > 0 }

fun box(): String {
    if (test(Box(5))?.value != 5) return "NOK"
    if (test(Box(0)) != null) return "NOK"
    if (test(null) != null) return "NOK"
    return "OK"
}
