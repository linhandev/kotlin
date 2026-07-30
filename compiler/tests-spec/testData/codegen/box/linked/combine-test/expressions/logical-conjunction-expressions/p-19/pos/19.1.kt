// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-conjunction-expressions -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 18 -> sentence 18
 *                type-system, introduction-1 -> paragraph 18 -> sentence 18
 *                expressions, elvis-operator-expressions -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: && binds tighter than Elvis
 */

// TESTCASE NUMBER: 1
fun test(flag: Boolean?): Boolean = flag ?: false && true

fun box(): String {
    if (test(null)) return "NOK"
    if (!test(true)) return "NOK"
    if (test(false)) return "NOK"
    return "OK"
}
