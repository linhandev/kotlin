// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 25 -> sentence 25
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 25 -> sentence 25
 *                declarations, property-declaration, delegated-property-declaration -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: safe call lazy delegated property both paths
 */

// TESTCASE NUMBER: 1
class Box {
    val value: Int by lazy { 42 }
}

fun test(box: Box?): Int? = box?.value

fun box(): String {
    if (test(Box()) != 42) return "NOK"
    if (test(null) != null) return "NOK"
    return "OK"
}
