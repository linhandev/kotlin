// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 36 -> sentence 36
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 36 -> sentence 36
 * NUMBER: 1
 * DESCRIPTION: return@forEach is local return and does not exit enclosing function
 */

// TESTCASE NUMBER: 1
fun test(xs: List<Int>): Int {
    xs.forEach { if (it < 0) return@forEach }
    return xs.size
}

fun box(): String {
    if (test(listOf(1, -2, 3)) != 3) return "NOK"
    return "OK"
}
