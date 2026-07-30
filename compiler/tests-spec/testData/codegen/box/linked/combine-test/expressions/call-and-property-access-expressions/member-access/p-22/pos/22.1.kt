// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 22 -> sentence 22
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: safe call then not-null assertion may throw NPE
 */

// TESTCASE NUMBER: 1
class Box(val value: Int)

fun test(box: Box?): Int = box?.value!!

fun box(): String {
    if (test(Box(42)) != 42) return "NOK"
    try {
        test(null)
        return "NOK"
    } catch (_: NullPointerException) {
        return "OK"
    }
}
