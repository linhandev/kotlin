// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 12 -> sentence 12
 *                expressions, call-and-property-access-expressions, callable-references -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: callable reference and trailing lambda resolve to equivalent map results
 */

// TESTCASE NUMBER: 1
fun test(xs: List<String>): List<Int> = xs.map(String::length)

fun test2(xs: List<String>): List<Int> = xs.map { it.length }

fun box(): String {
    val xs = listOf("a", "ab")
    if (test(xs) != listOf(1, 2)) return "NOK"
    if (test2(xs) != listOf(1, 2)) return "NOK"
    return "OK"
}
