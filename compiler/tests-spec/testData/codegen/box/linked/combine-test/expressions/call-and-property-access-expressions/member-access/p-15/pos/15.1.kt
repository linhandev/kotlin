// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 15 -> sentence 15
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 15 -> sentence 15
 *                expressions, when-expressions -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: when condition safe call property three paths
 */

// TESTCASE NUMBER: 1
class Box(val value: Int)

fun test(box: Box?): String = when {
    box?.value == 42 -> "found"
    else -> "not found"
}

fun box(): String {
    if (test(Box(42)) != "found") return "NOK"
    if (test(Box(1)) != "not found") return "NOK"
    if (test(null) != "not found") return "NOK"
    return "OK"
}
