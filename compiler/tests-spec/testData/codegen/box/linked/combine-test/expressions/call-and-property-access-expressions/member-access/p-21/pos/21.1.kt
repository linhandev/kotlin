// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 21 -> sentence 21
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: takeUnless then safe property access; three paths
 */

// TESTCASE NUMBER: 1

class Box(val value: Int)

fun test(box: Box?): Int? = box?.takeUnless { it.value > 10 }?.value

fun box(): String {
    if (test(Box(5)) != 5) return "NOK"
    if (test(Box(15)) != null) return "NOK"
    if (test(null) != null) return "NOK"
    return "OK"
}
