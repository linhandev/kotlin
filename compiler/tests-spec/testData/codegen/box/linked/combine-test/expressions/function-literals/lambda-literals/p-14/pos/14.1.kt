// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: nested destructuring via lambda parameter and body destructuring is allowed
 */

// TESTCASE NUMBER: 1
fun test(p: Pair<Pair<Int, Int>, Int>): Int = p.let { (ab, c) ->
    val (a, b) = ab
    a + b + c
}

fun box(): String {
    if (test((1 to 2) to 3) != 6) return "NOK"
    return "OK"
}
