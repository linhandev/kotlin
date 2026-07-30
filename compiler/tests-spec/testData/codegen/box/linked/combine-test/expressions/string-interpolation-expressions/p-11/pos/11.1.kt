// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 11 -> sentence 11
 *                expressions, comparison-expressions -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: interpolation expression may contain comparison operator
 */

// TESTCASE NUMBER: 1
fun test(a: Int, b: Int): String = "cmp=${a > b}"

fun box(): String {
    if (test(3, 2) != "cmp=true") return "NOK"
    if (test(1, 5) != "cmp=false") return "NOK"
    return "OK"
}
