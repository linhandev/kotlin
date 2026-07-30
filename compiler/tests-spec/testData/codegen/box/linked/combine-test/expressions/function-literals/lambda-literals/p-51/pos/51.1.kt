// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 51 -> sentence 51
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 51 -> sentence 51
 * NUMBER: 1
 * DESCRIPTION: non-local return from onEach extension lambda
 */

// TESTCASE NUMBER: 1
fun test(xs: List<Int>): Int {
    xs.onEach { if (it == 0) return -1 }
    return 1
}

fun box(): String {
    if (test(listOf(1, 0, 2)) != -1) return "NOK"
    if (test(listOf(1, 2)) != 1) return "NOK"
    return "OK"
}
