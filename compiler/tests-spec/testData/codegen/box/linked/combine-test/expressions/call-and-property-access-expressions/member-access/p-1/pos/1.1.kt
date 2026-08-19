// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 1 -> sentence 1
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: safe call property: non-null value and null receiver paths
 */

// TESTCASE NUMBER: 1

class Box(val value: Int)

fun test(box: Box?): Int? = box?.value

fun box(): String {
    if (test(Box(42)) != 42) return "NOK"
    if (test(null) != null) return "NOK"
    return "OK"
}
