// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 6 -> sentence 6
 *                overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: collection higher-order trailing lambda combined with property access chain
 */

// TESTCASE NUMBER: 1
fun test(xs: List<Int>): Int = xs.filter { it > 0 }.sum()

fun box(): String {
    if (test(listOf(-1, 2, 3)) != 5) return "NOK"
    if (test(listOf(0, -2)) != 0) return "NOK"
    return "OK"
}
