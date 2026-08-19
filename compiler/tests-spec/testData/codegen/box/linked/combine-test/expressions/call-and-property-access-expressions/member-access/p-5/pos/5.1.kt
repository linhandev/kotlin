// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 5 -> sentence 5
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: unnecessary safe call on non-null still typed Int? with non-null value
 */

// TESTCASE NUMBER: 1

class Box(val value: Int)

fun test(box: Box): Int? = box?.value

fun box(): String {
    val r: Int? = test(Box(42))
    if (r == null) return "NOK"
    if (r != 42) return "NOK"
    return "OK"
}
