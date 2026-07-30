// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 14 -> sentence 14
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 14 -> sentence 14
 *                expressions, elvis-operator-expressions -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: nested nullable safe call with elvis: property-null, value, and receiver-null
 */

// TESTCASE NUMBER: 1

class Box(val value: Int?)

fun test(box: Box?): Int = box?.value ?: 0

fun box(): String {
    if (test(Box(null)) != 0) return "NOK"
    if (test(Box(5)) != 5) return "NOK"
    if (test(null) != 0) return "NOK"
    return "OK"
}
