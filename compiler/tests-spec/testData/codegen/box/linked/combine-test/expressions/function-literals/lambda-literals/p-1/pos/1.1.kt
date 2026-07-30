// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: lambda parameter can destructure Pair
 */

// TESTCASE NUMBER: 1
fun test(ps: List<Pair<Int, String>>): List<Int> = ps.map { (k, v) -> k + v.length }

fun box(): String {
    if (test(listOf(1 to "ab", 2 to "c")) != listOf(3, 3)) return "NOK"
    return "OK"
}
