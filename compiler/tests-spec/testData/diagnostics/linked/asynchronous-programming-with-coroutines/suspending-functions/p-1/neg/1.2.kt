// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: delegation-related operator suspend contains is not supported
 */

// TESTCASE NUMBER: 1
fun case_1() {
    class Delegate18012 {
        <!UNSUPPORTED!>operator<!> suspend fun contains(element: String): Boolean = true
    }
}
