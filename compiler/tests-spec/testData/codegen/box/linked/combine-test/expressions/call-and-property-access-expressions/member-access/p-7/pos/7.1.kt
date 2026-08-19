// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 7 -> sentence 7
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 7 -> sentence 7
 *                expressions, elvis-operator-expressions -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: safe call on Int property with elvis default for null receiver
 */

// TESTCASE NUMBER: 1

class Box(val value: Int)

fun test(box: Box?): Int = box?.value ?: 0

fun box(): String {
    if (test(Box(42)) != 42) return "NOK"
    if (test(null) != 0) return "NOK"
    return "OK"
}
