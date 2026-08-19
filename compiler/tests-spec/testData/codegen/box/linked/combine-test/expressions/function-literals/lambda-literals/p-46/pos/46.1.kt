/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 46 -> sentence 46
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 46 -> sentence 46
 * NUMBER: 1
 * DESCRIPTION: lambda non-local return versus anonymous function local return under inline
 */

// TESTCASE NUMBER: 1
inline fun invokeRun(block: () -> Unit): Unit = block()

fun testLambda(): Int {
    invokeRun { return 2 }
    return 1
}

fun testAnon(): Int {
    invokeRun(fun() { return })
    return 1
}

fun box(): String {
    if (testLambda() != 2) return "NOK"
    if (testAnon() != 1) return "NOK"
    return "OK"
}
