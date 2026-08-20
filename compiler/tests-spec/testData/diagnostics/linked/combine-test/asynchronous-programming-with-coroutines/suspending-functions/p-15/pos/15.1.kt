// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 15 -> sentence 15
 *                statements, loop-statements -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: for-loop body inside a suspend fun may call suspend functions type inference
 * HELPERS: checkType
 */

suspend fun step56115(x: Int): Int = x
suspend fun sum56115(xs: List<Int>): Int {
    var s = 0
    for (x in xs) {
        s += step56115(x)
    }
    return s
}

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<suspend (List<Int>) -> Int>(::sum56115)
}
