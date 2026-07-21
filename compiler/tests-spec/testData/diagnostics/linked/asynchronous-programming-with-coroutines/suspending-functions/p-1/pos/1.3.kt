// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -SUSPENSION_CALL_MUST_BE_USED_AS_RETURN_VALUE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: member and local functions may be marked suspending
 */

// TESTCASE NUMBER: 1
fun case_1() {
    class Holder18003 {
        suspend fun member(): Int = 1

        suspend fun withLocal(): Int {
            suspend fun local(): Int = 2
            return local()
        }
    }
}
