// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 9 -> sentence 9
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: safe call assignment writes on non-null; no-op on null
 */

// TESTCASE NUMBER: 1

class Box(var value: Int)

fun test(box: Box?) {
    box?.value = 42
}

fun box(): String {
    val b = Box(7)
    test(null)
    if (b.value != 7) return "NOK"
    test(b)
    if (b.value != 42) return "NOK"
    return "OK"
}
