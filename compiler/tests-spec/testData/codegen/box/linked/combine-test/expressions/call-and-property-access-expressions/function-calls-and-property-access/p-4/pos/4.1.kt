// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 4 -> sentence 4
 *                overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: trailing lambda can follow regular positional arguments
 */

// TESTCASE NUMBER: 1
fun fold(init: Int, xs: List<Int>, acc: (Int, Int) -> Int): Int = xs.fold(init, acc)

fun test(): Int = fold(0, listOf(1, 2)) { a, b -> a + b }

fun box(): String {
    if (test() != 3) return "NOK"
    if (fold(10, listOf(5)) { a, b -> a - b } != 5) return "NOK"
    return "OK"
}
