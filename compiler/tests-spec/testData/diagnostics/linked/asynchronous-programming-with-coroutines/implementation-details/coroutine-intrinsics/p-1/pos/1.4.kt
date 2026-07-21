// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, implementation-details, coroutine-intrinsics -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: createCoroutineUnintercepted supports explicit receiver suspend function type
 */

import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*

// TESTCASE NUMBER: 1
fun case_1() {

    class Receiver18164

    suspend fun Receiver18164.work(): String = "OK"

    fun start18164() {
        val block: suspend Receiver18164.() -> String = { work() }
        block.createCoroutineUnintercepted(
            Receiver18164(),
            object : Continuation<String> {
                override val context: CoroutineContext = EmptyCoroutineContext
                override fun resumeWith(result: Result<String>) {}
            }
        )
    }
}
