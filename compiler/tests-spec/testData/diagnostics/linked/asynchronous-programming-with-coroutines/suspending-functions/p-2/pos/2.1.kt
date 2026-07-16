// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -SUSPENSION_CALL_MUST_BE_USED_AS_RETURN_VALUE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: call to another suspending function is a suspension point
 */

// TESTCASE NUMBER: 1
fun case_1() {
    suspend fun callee18021(): Int = 1

    suspend fun caller18021(): Int = callee18021()
}
