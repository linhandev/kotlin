// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-conjunction-expressions -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: comparisons bind tighter than &&
 */

// TESTCASE NUMBER: 1
fun test(x: Int): Boolean = x > 0 && x < 10

fun box(): String {
    if (!test(5)) return "NOK"
    if (test(0)) return "NOK"
    if (test(10)) return "NOK"
    return "OK"
}
