// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 5 -> sentence 5
 *                overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: explicit lambda in parentheses and trailing lambda forms are equivalent
 */

// TESTCASE NUMBER: 1
fun test(xs: List<Int>): List<Int> = xs.map({ it * 2 })

fun test2(xs: List<Int>): List<Int> = xs.map { it * 2 }

fun box(): String {
    val xs = listOf(1, 2, 3)
    if (test(xs) != listOf(2, 4, 6)) return "NOK"
    if (test2(xs) != listOf(2, 4, 6)) return "NOK"
    if (test(emptyList()) != emptyList<Int>()) return "NOK"
    return "OK"
}
