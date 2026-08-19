// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: single-parameter body destructuring is equivalent to parameter destructuring
 */

// TESTCASE NUMBER: 1
fun test(ps: List<Pair<Int, Int>>): List<Int> = ps.map { it: Pair<Int, Int> ->
    val (a, b) = it
    a + b
}

fun box(): String {
    if (test(listOf(1 to 2, 3 to 4)) != listOf(3, 7)) return "NOK"
    return "OK"
}
