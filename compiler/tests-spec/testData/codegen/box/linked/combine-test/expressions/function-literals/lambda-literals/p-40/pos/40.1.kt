/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 40 -> sentence 40
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 40 -> sentence 40
 * NUMBER: 1
 * DESCRIPTION: nested inline higher-order non-local return reaches outermost named function
 */

// TESTCASE NUMBER: 1
inline fun outer(block: () -> Unit): Unit = block()
inline fun inner(block: () -> Unit): Unit = outer(block)

fun test(): Int {
    inner { return 5 }
    return 0
}

fun box(): String {
    if (test() != 5) return "NOK"
    return "OK"
}
