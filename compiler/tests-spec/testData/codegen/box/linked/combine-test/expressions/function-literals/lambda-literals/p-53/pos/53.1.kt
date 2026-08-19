// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 53 -> sentence 53
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 53 -> sentence 53
 * NUMBER: 1
 * DESCRIPTION: for-loop return exits enclosing function as control contrast to lambda
 */

// TESTCASE NUMBER: 1
fun test(xs: List<Int>): Int {
    for (x in xs) {
        if (x < 0) return -1
    }
    return 0
}

fun box(): String {
    if (test(listOf(1, -2)) != -1) return "NOK"
    if (test(listOf(1, 2)) != 0) return "NOK"
    return "OK"
}
