// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 2 -> sentence 2
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: chained safe call: non-null 7 and null receiver
 */

// TESTCASE NUMBER: 1
class Outer(val inner: Inner)
class Inner(val value: Int)

fun test(outer: Outer?): Int? = outer?.inner?.value

fun box(): String {
    if (test(Outer(Inner(7))) != 7) return "NOK"
    if (test(null) != null) return "NOK"
    return "OK"
}
