// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 18 -> sentence 18
 *                expressions, jump-expressions, return-expressions -> paragraph 18 -> sentence 18
 *                declarations, function-declaration -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: bare return inside inline forEach lambda may non-locally exit the enclosing suspend function type inference
 * HELPERS: checkType
 */

suspend fun s56118(xs: List<Int>): Int {
    xs.forEach {
        if (it < 0) return -1
    }
    return 0
}

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<suspend (List<Int>) -> Int>(::s56118)
}
