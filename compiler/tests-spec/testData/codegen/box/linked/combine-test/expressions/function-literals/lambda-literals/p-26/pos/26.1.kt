// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: fold second parameter can be Pair-destructured
 */

// TESTCASE NUMBER: 1
fun test(ps: List<Pair<Int, Int>>): Int =
    ps.fold(0) { acc, (a, b) -> acc + a + b }

fun box(): String {
    if (test(listOf(1 to 2, 3 to 4)) != 10) return "NOK"
    return "OK"
}
