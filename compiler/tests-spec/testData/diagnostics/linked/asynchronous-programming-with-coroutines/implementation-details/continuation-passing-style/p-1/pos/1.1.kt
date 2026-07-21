// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, implementation-details, continuation-passing-style -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: ordinary suspending function compiles with implicit CPS transformation
 */

// TESTCASE NUMBER: 1
fun case_1() {
    suspend fun cps18081(): Int = 42
}
