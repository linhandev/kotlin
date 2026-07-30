// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 18 -> sentence 18
 *                expressions, jump-expressions, return-expressions -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: labeled return can be used inside trailing lambda
 */

// TESTCASE NUMBER: 1
fun test(xs: List<Int>): List<Int> = xs.map { if (it < 0) return@map 0 else it }

fun box(): String {
    if (test(listOf(-1, 2, 3)) != listOf(0, 2, 3)) return "NOK"
    if (test(listOf(1, 2)) != listOf(1, 2)) return "NOK"
    return "OK"
}
