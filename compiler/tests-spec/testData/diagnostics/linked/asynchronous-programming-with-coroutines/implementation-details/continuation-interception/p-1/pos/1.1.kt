// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, implementation-details, continuation-interception -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: ContinuationInterceptor interceptContinuation wraps continuation
 */

import kotlin.coroutines.*

// TESTCASE NUMBER: 1
class Interceptor18131 : AbstractCoroutineContextElement(ContinuationInterceptor), ContinuationInterceptor {
    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> =
        WrappedContinuation18131(continuation)

    override fun releaseInterceptedContinuation(continuation: Continuation<*>) {}
}

class WrappedContinuation18131<T>(private val delegate: Continuation<T>) : Continuation<T> {
    override val context: CoroutineContext = delegate.context

    override fun resumeWith(result: Result<T>) {
        delegate.resumeWith(result)
    }
}

fun case_1() {
    val interceptor = Interceptor18131()
}
