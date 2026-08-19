// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 50 -> sentence 50
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 50 -> sentence 50
 *                expressions, try-expressions -> paragraph 50 -> sentence 50
 * NUMBER: 1
 * DESCRIPTION: non-local return from forEach still executes finally block
 */

// TESTCASE NUMBER: 1
var fin = false

fun test(xs: List<Int>): Int {
    try {
        xs.forEach { return 1 }
    } finally {
        fin = true
    }
    return 0
}

fun box(): String {
    if (test(listOf(1)) != 1) return "NOK"
    if (!fin) return "NOK"
    return "OK"
}
