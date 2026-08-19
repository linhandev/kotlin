// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 10 -> sentence 10
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 10 -> sentence 10
 *                expressions, indexing-expressions -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: safe get on nullable IntArray both paths
 */

// TESTCASE NUMBER: 1
fun test(arr: IntArray?): Int? = arr?.get(0)

fun box(): String {
    if (test(intArrayOf(7)) != 7) return "NOK"
    if (test(null) != null) return "NOK"
    return "OK"
}
