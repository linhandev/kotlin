/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 45 -> sentence 45
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 45 -> sentence 45
 * NUMBER: 1
 * DESCRIPTION: anonymous function passed to inline still returns only from anonymous function
 */

// TESTCASE NUMBER: 1
inline fun invokeRun(block: () -> Unit): Unit = block()

fun test(): Int {
    invokeRun(fun() { return })
    return 1
}

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
