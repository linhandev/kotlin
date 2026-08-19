// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 4 -> sentence 4
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: null as Box? safe call returns null typed Int?
 */

// TESTCASE NUMBER: 1
class Box(val value: Int)

fun test(): Int? = (null as Box?)?.value

fun box(): String {
    val r: Int? = test()
    if (r != null) return "NOK: not null"
    return "OK"
}
