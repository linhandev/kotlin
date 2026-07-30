// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: zip result pairs can be destructured in a lambda
 */

// TESTCASE NUMBER: 1
fun test(a: List<Int>, b: List<Int>): List<Int> = a.zip(b).map { (x, y) -> x + y }

fun box(): String {
    if (test(listOf(1, 2), listOf(10, 20)) != listOf(11, 22)) return "NOK"
    return "OK"
}
