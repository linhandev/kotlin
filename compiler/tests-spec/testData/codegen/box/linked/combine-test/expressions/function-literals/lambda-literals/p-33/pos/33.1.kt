/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 33 -> sentence 33
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 33 -> sentence 33
 * NUMBER: 1
 * DESCRIPTION: custom inline higher-order allows non-local return from lambda
 */

// TESTCASE NUMBER: 1
inline fun invokeRun(block: () -> Unit): Unit = block()

fun test(): Int {
    invokeRun { return 2 }
    return 1
}

fun box(): String {
    if (test() != 2) return "NOK"
    return "OK"
}
