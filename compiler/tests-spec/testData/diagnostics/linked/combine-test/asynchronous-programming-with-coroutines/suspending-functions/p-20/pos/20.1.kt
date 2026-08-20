// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 20 -> sentence 20
 *                expressions, jump-expressions, return-expressions -> paragraph 20 -> sentence 20
 *                expressions, when-expressions -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: suspend lambda may use labeled return inside when for a local return type inference
 * HELPERS: checkType
 */

suspend fun run56120(block: suspend () -> Int): Int = block()
suspend fun s56120(): Int = run56120 {
    when {
        true -> return@run56120 2
        else -> 0
    }
}

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<suspend () -> Int>(::s56120)
}
