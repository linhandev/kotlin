// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -NOTHING_TO_INLINE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 32 -> sentence 32
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 32 -> sentence 32
 *                declarations, function-declaration -> paragraph 32 -> sentence 32
 *                type-inference, introduction-1 -> paragraph 32 -> sentence 32
 * NUMBER: 1
 * DESCRIPTION: suspend function type can participate in inline generic type-argument inference type inference
 * HELPERS: checkType
 */

inline fun <reified T> select56132(x: T): T = x
suspend fun s56132(): Int {
    val block: suspend () -> Int = { 7 }
    return select56132(block)()
}

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<suspend () -> Int>(::s56132)
}
