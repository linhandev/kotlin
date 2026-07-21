// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -INFERRED_INTO_DECLARED_UPPER_BOUNDS
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, implementation-details, coroutine-intrinsics -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: Continuation.intercepted returns intercepted continuation in suspending context
 */

import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*

// TESTCASE NUMBER: 1
fun case_1() {

    suspend fun useIntercepted18163() {
        suspendCoroutineUninterceptedOrReturn { continuation ->
            continuation.intercepted()
            COROUTINE_SUSPENDED
        }
    }
}
