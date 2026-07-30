// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-conjunction-expressions -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 10 -> sentence 10
 *                type-system, introduction-1 -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: a == true && b handles nullable Boolean
 */

// TESTCASE NUMBER: 1
fun test(a: Boolean?, b: Boolean): Boolean = (a == true) && b

fun box(): String {
    if (test(null, true)) return "NOK"
    if (!test(true, true)) return "NOK"
    if (test(true, false)) return "NOK"
    if (test(false, true)) return "NOK"
    return "OK"
}
