// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 24 -> sentence 24
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: safe call let invokes lambda only when non-null
 */

// TESTCASE NUMBER: 1
class Box(val value: Int)

fun test(box: Box?, block: (Box) -> Unit) {
    box?.let(block)
}

fun box(): String {
    var seen = 0
    test(Box(3)) { seen = it.value }
    if (seen != 3) return "NOK"
    test(null) { seen = -1 }
    if (seen != 3) return "NOK"
    return "OK"
}
