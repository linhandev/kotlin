// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 15 -> sentence 15
 *                overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: chained trailing lambdas bind to separate calls in a property access chain
 */

// TESTCASE NUMBER: 1
fun test(xs: List<Int>): List<String> = xs.filter { it > 0 }.map { it.toString() }

fun box(): String {
    if (test(listOf(-1, 2, 3)) != listOf("2", "3")) return "NOK"
    if (test(listOf(0, -2)) != emptyList<String>()) return "NOK"
    return "OK"
}
