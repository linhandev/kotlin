// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 17 -> sentence 17
 *                expressions, jump-expressions, return-expressions -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: bare return inside for-loop of a suspend fun still exits the outer suspend function type inference
 * HELPERS: checkType
 */

suspend fun s56117(xs: List<Int>): Int {
    for (x in xs) {
        if (x < 0) return -1
    }
    return 0
}

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<suspend (List<Int>) -> Int>(::s56117)
}
