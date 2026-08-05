// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 14 -> sentence 14
 *                expressions, when-expressions -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: when branches inside a suspend fun may call suspend functions type inference
 * HELPERS: checkType
 */

suspend fun zero56114(): Int = 0
suspend fun pick56114(n: Int): Int = when (n) {
    0 -> zero56114()
    else -> n
}

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<suspend (Int) -> Int>(::pick56114)
}
