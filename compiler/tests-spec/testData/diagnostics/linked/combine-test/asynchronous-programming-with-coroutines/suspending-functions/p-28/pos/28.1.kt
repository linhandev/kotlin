// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 28 -> sentence 28
 *                declarations, function-declaration -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: tailrec suspend fun can be declared and executed type inference
 * HELPERS: checkType
 */

tailrec suspend fun fact56128(n: Int, acc: Int = 1): Int =
    if (n <= 1) acc else fact56128(n - 1, acc * n)

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<suspend (Int, Int) -> Int>(::fact56128)
}
