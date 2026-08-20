// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 7 -> sentence 7
 *                type-system, type-kinds, function-types -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: suspend function type is incompatible with a regular function type parameter
 */

suspend fun s56107(): Int = 1
fun take56107(f: () -> Int): Int = f()

// TESTCASE NUMBER: 1
suspend fun use56107(): Int = take56107(<!TYPE_MISMATCH!>::s56107<!>)
