// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 18 -> sentence 18
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: safe call apply mutates non-null; null no-op
 */

// TESTCASE NUMBER: 1
class Box(var value: Int)

fun test(box: Box?): Box? = box?.apply { value = 42 }

fun box(): String {
    val b = Box(0)
    if (test(b)?.value != 42) return "NOK"
    if (test(null) != null) return "NOK"
    return "OK"
}
