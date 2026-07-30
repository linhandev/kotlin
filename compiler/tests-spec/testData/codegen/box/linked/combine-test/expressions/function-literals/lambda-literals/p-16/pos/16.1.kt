// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: chained higher-order calls can use consecutive destructuring lambdas
 */

// TESTCASE NUMBER: 1
fun test(xs: List<Pair<Int, Int>>): List<Int> =
    xs.filter { (a, _) -> a > 0 }.map { (a, b) -> a + b }

fun box(): String {
    if (test(listOf((-1) to 9, 1 to 2, 3 to 4)) != listOf(3, 7)) return "NOK"
    return "OK"
}
