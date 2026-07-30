// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 13 -> sentence 13
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: safe call to nested nullable property remains Int?
 */

// TESTCASE NUMBER: 1
class Box(val value: Int?)

fun test(box: Box?): Int? = box?.value

fun box(): String {
    if (test(Box(null)) != null) return "NOK"
    if (test(Box(3)) != 3) return "NOK"
    if (test(null) != null) return "NOK"
    return "OK"
}
