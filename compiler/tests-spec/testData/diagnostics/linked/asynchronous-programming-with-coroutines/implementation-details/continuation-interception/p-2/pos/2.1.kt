// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, implementation-details, continuation-interception -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: full ContinuationInterceptor with releaseInterceptedContinuation
 */

import kotlin.coroutines.*

// TESTCASE NUMBER: 1
class FullInterceptor18141 : AbstractCoroutineContextElement(ContinuationInterceptor), ContinuationInterceptor {
    private val intercepted = mutableSetOf<Continuation<*>>()

    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> {
        val wrapped = WrappedContinuation18141(continuation)
        intercepted += wrapped
        return wrapped
    }

    override fun releaseInterceptedContinuation(continuation: Continuation<*>) {
        intercepted -= continuation
    }
}

class WrappedContinuation18141<T>(private val delegate: Continuation<T>) : Continuation<T> {
    override val context: CoroutineContext = delegate.context

    override fun resumeWith(result: Result<T>) {
        delegate.resumeWith(result)
    }
}

fun case_1() {
    val interceptor = FullInterceptor18141()
}
