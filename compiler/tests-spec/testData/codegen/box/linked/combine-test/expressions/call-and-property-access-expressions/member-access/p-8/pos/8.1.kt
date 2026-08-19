// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 8 -> sentence 8
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: safe call member function both paths
 */

// TESTCASE NUMBER: 1
class Box {
    fun getValue(): Int = 42
}

fun test(box: Box?): Int? = box?.getValue()

fun box(): String {
    if (test(Box()) != 42) return "NOK"
    if (test(null) != null) return "NOK"
    return "OK"
}
