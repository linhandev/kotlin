/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 39 -> sentence 39
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 39 -> sentence 39
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 39 -> sentence 39
 * NUMBER: 1
 * DESCRIPTION: trailing lambda into custom inline allows non-local return
 */

// TESTCASE NUMBER: 1
inline fun <R> applyBlock(block: () -> R): R = block()

fun test(): Int {
    applyBlock { return 3 }
}

fun box(): String {
    if (test() != 3) return "NOK"
    return "OK"
}
