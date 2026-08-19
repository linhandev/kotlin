// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 38 -> sentence 38
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 38 -> sentence 38
 * NUMBER: 1
 * DESCRIPTION: return@map ends current element mapping without exiting function
 */

// TESTCASE NUMBER: 1
fun test(xs: List<Int>): List<Int> = xs.map { if (it < 0) return@map 0 else it * 2 }

fun box(): String {
    if (test(listOf(-1, 2, 3)) != listOf(0, 4, 6)) return "NOK"
    return "OK"
}
