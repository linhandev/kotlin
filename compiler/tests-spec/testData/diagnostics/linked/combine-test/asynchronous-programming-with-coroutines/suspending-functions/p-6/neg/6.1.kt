// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 6 -> sentence 6
 *                declarations, function-declaration -> paragraph 6 -> sentence 6
 *                expressions, when-expressions -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: top-level non-suspending function cannot call local suspend fun from when
 */

// TESTCASE NUMBER: 1
fun case_1(flag: Boolean): Int {
    suspend fun inner56106(): Int = 1
    return when {
        flag -> <!ILLEGAL_SUSPEND_FUNCTION_CALL!>inner56106<!>()
        else -> 0
    }
}
