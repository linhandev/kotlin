// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 23 -> sentence 23
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 23 -> sentence 23
 *                type-inference, smart-casts -> paragraph 23 -> sentence 23
 *                expressions, elvis-operator-expressions -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: smart cast branch and safe-call elvis branch
 */

// TESTCASE NUMBER: 1
class Box(val value: Int)

fun test(box: Box?): Int {
    if (box != null) return box.value
    return box?.value ?: 0
}

fun box(): String {
    if (test(Box(42)) != 42) return "NOK"
    if (test(null) != 0) return "NOK"
    return "OK"
}
