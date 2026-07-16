// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -SUSPENSION_CALL_MUST_BE_USED_AS_RETURN_VALUE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, implementation-details, coroutine-state-machine -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: suspend lambda preserves local variable across suspend call
 */

// TESTCASE NUMBER: 1
fun case_1() {
    fun builder18111(c: suspend () -> Unit) {}

    fun test18111() {
        builder18111 {
            var local = 0
            suspend fun increment() {
                local++
            }
            increment()
        }
    }
}
