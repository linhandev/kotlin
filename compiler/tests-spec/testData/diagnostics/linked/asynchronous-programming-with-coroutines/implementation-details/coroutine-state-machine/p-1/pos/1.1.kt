// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -SUSPENSION_CALL_MUST_BE_USED_AS_RETURN_VALUE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, implementation-details, coroutine-state-machine -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: suspending function with multiple suspend calls compiles as state machine
 */

// TESTCASE NUMBER: 1
fun case_1() {
    suspend fun step18101(): Int = 1

    suspend fun stateMachine18101(): Int {
        val a = step18101()
        val b = step18101()
        return a + b
    }
}
