// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 17 -> sentence 17
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 17 -> sentence 17
 *                expressions, elvis-operator-expressions -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: safe call let with elvis both paths
 */

// TESTCASE NUMBER: 1
class Box(val value: Int)

fun test(box: Box?): Int = box?.let { it.value } ?: 0

fun box(): String {
    if (test(Box(42)) != 42) return "NOK"
    if (test(null) != 0) return "NOK"
    return "OK"
}
