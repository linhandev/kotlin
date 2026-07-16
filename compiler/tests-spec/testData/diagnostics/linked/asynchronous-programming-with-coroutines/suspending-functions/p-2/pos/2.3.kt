// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -SUSPENSION_CALL_MUST_BE_USED_AS_RETURN_VALUE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 2 -> sentence 2
 * NUMBER: 3
 * DESCRIPTION: multiple suspending function calls form multiple suspension points
 */

// TESTCASE NUMBER: 1
fun case_1() {
    suspend fun first18023(): Int = 1

    suspend fun second18023(): Int = 2

    suspend fun chain18023(): Int {
        val a = first18023()
        val b = second18023()
        return a + b
    }
}
