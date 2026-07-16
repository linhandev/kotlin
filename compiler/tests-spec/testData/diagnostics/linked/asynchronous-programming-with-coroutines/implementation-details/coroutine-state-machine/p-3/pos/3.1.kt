// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -SUSPENSION_CALL_MUST_BE_USED_AS_RETURN_VALUE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, implementation-details, coroutine-state-machine -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: suspend lambda with two suspend calls and early return compiles
 */

// TESTCASE NUMBER: 1
fun case_1() {
    fun builder18121(c: suspend () -> String) {}

    var earlyReturn18121 = true

    fun test18121() {
        builder18121 {
            suspend fun step1(): Int = 1
            suspend fun step2(): Int = 2
            if (earlyReturn18121) return@builder18121 "early"
            step1()
            step2()
            "done"
        }
    }
}
