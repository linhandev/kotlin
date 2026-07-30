/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 35 -> sentence 35
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 35 -> sentence 35
 * NUMBER: 1
 * DESCRIPTION: non-inline higher-order uses labeled local return; outer returns 1
 */

// TESTCASE NUMBER: 1
fun runNonInline(block: () -> Unit): Unit = block()

fun test(): Int {
    runNonInline { return@runNonInline }
    return 1
}

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
