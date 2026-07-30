// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 31 -> sentence 31
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 31 -> sentence 31
 * NUMBER: 1
 * DESCRIPTION: inline forEach bare return exits enclosing function when list has negative
 */

// TESTCASE NUMBER: 1
fun test(xs: List<Int>): Int {
    xs.forEach { if (it < 0) return -1 }
    return 1
}

fun box(): String {
    if (test(listOf(1, -2, 3)) != -1) return "NOK"
    if (test(listOf(1, 2, 3)) != 1) return "NOK"
    return "OK"
}
