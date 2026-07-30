// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 11 -> sentence 11
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 11 -> sentence 11
 *                expressions, indexing-expressions -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: safe get on nullable List both paths
 */

// TESTCASE NUMBER: 1
fun test(list: List<Int>?): Int? = list?.get(0)

fun box(): String {
    if (test(listOf(9)) != 9) return "NOK"
    if (test(null) != null) return "NOK"
    return "OK"
}
