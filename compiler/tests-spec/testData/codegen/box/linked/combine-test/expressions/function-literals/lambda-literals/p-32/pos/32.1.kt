// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 32 -> sentence 32
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 32 -> sentence 32
 * NUMBER: 1
 * DESCRIPTION: inline map non-local return with value when element equals zero
 */

// TESTCASE NUMBER: 1
fun test(xs: List<Int>): Int {
    return xs.map { if (it == 0) return 0 else it * 2 }.first()
}

fun box(): String {
    if (test(listOf(1, 0, 2)) != 0) return "NOK"
    if (test(listOf(1, 2)) != 2) return "NOK"
    return "OK"
}
